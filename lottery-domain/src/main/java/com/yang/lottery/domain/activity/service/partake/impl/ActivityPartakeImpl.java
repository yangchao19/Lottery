package com.yang.lottery.domain.activity.service.partake.impl;

import com.yang.db.router.strategy.IDBRouterStrategy;
import com.yang.lottery.common.Constants;
import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.yang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.yang.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.yang.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.yang.lottery.domain.activity.service.partake.BaseActivityPartake;
import com.yang.lottery.domain.support.ids.IIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId,uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) {
        // 校验：活动状态
        if (!Constants.ActivityState.DOING.getCode().equals(bill.getState())) {
            logger.warn("获得那个当前状态非可用 state：{}",bill.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR,"当前活动状态非可用");
        }

        //校验：活动日期
        if (bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())) {
            logger.warn("活动时间范围非可用，beginDateTime：{} endDateTime：{}",bill.getBeginDateTime(),bill.getEndDateTime());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR,"当前时间范围非可用");
        }

        //检验：活动库存
        if (bill.getStockSurplusCount() <= 0) {
            logger.warn("活动剩余库存非可用 stockSurplusCount:{}",bill.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR,"当前剩余库存非可用");
        }

        //校验个人库存 -个人活动剩余可领取次数
        if (null != bill.getUserTakeLeftCount() && bill.getUserTakeLeftCount() <= 0) {
            logger.warn("个人领取次数非可用 userTakeLeftCount:{}",bill.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR,"个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if(0 == count) {
            logger.error("扣减活动库存失败 activityId:{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result graActivity(PartakeReq partake, ActivityBillVO bill,Long takeId) {
        try {
            dbRouter.doRouter(partake.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(),bill.getActivityName(),bill.getTakeCount(),bill.getUserTakeLeftCount(),partake.getuId(),partake.getPartakeDate());
                    if(0 == updateCount) {
                        status.setRollbackOnly();
                        logger.error("领取活动，扣减个人已参与次数失败 activityId：{} uid :{}",partake.getActivityId(),partake.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }
                    //插入领取活动信息
                    userTakeActivityRepository.takeActivity(bill.getActivityId(),bill.getActivityName(),bill.getStrategyId(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getuId(), partake.getPartakeDate(), takeId);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("领取活动，唯一索引冲突 activity：{} uId：{}",partake.getActivityId(),partake.getuId());
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return  Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    //锁定活动领取记录
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrder.getuId(),drawOrder.getActivityId(),drawOrder.getTakeId());
                    if (0 == lockCount) {
                        status.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖次数已耗完 activityId ；{} uId： {}",drawOrder.getActivityId(),drawOrder.getActivityId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    //保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId： {} uid:{}",drawOrder.getActivityId(),drawOrder.getuId());
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    @Override
    public void updateInvoiceMaState(String uId, Long orderId, Integer mqState) {

    }
}
