package com.yang.lottery.domain.activity.service.partake;

import com.yang.lottery.common.Constants;
import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.res.PartakeResult;
import com.yang.lottery.domain.activity.model.res.StockResult;
import com.yang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.yang.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.yang.lottery.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动领取模板抽象类
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public PartakeResult doPartake(PartakeReq req) {


        //1. 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getuId());
        if(null != userTakeActivityVO) {
            return buildPartakeResult(userTakeActivityVO.getStrategyId(),userTakeActivityVO.getTakeId(), Constants.ResponseCode.NOT_CONSUMED_TAKE);
        }


        //2. 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        //3. 活动信息校验处理【活动库存、状态、日期、本人参与次数】
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(),checkResult.getInfo());
        }

        //4. 扣减活动库存,通过Redis【活动库存扣减编号，作为锁的Key，缩小颗粒度】 Begin
        StockResult subtractionActivityResult = this.subtractionActivityStockByRedis(req.getuId(), req.getActivityId(), activityBillVO.getStockCount());
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), subtractionActivityResult.getCode());
            return new PartakeResult(subtractionActivityResult.getCode(),subtractionActivityResult.getInfo());
        }

        //5. 领取活动信息【个人用户把活动信息写入到用户表】
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result garResult = this.graActivity(req, activityBillVO,takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(garResult.getCode())) {
            this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(),subtractionActivityResult.getCode());
            return new PartakeResult(garResult.getCode(),garResult.getInfo());
        }

        //6. 扣减活动库存，通过Redis End
        this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(),Constants.ResponseCode.SUCCESS.getCode());

        return buildPartakeResult(activityBillVO.getStrategyId(), takeId, activityBillVO.getStockCount(), subtractionActivityResult.getStockSurplusCount(), Constants.ResponseCode.SUCCESS);

    }

    /**
     * 封装结果【返回策略id，用于继续完成抽奖步骤】
     * @param strategyId 策略id
     * @param takeId     领取id
     * @return 封装结果
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId, Integer stockCount, Integer stockSurplusCount, Constants.ResponseCode code) {
        PartakeResult partakeResult = new PartakeResult(code.getCode(), code.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        partakeResult.setStockCount(stockCount);
        partakeResult.setStockSurplusCount(stockSurplusCount);
        return partakeResult;
    }

    private PartakeResult buildPartakeResult(Long strategyId, Long takeId, Constants.ResponseCode code) {
        PartakeResult partakeResult = new PartakeResult(code.getCode(), code.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }



    /**
     *  查询是否存在未执行抽奖的领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * @param activityId 活动id
     * @param uId 用户id
     * @return 领取单
     */
    protected  abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId,String uId);

    /**
     * 活动信息检验处理
     * @param partake 参与活动请求
     * @param bill    活动状态
     * @return        校验结果
     */
    protected abstract Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) ;

    /**
     * 扣减活动库存
     * @param req 参与活动请求
     * @return    扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);

    /**
     * 领取活动
     * @param partake 参与活动请求
     * @param bill    活动账单
     * @param takeId
     * @return        领取结果
     */
    protected abstract Result graActivity(PartakeReq partake, ActivityBillVO bill,Long takeId);

    /**
     * 通过Redis 扣减活动库存
     * @param uId 用户id
     * @param activityId 活动id
     * @param stockCount 总库存
     * @return 扣减结果
     */
    protected abstract StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount);

    /**
     *  恢复活动库存 通过Redis 【如果非常异常，则需要进行缓存库存恢复，只保证不超卖的特性，所以不保证一定能恢复占用库存，另外最终可以由任务进行补偿库存】
     * @param activityId 活动id
     * @param tokenKey  分布式 Key 用户清理
     * @param code 状态
     */
    protected abstract void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code);

}
