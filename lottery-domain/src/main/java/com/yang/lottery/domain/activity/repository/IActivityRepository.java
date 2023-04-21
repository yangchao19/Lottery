package com.yang.lottery.domain.activity.repository;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.res.StockResult;
import com.yang.lottery.domain.activity.model.vo.*;

import java.util.List;

/**
 * @description: 活动仓库服务
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public interface IActivityRepository {
    /**
     * 添加活动配置
     * @param activity 活动配置
     */
    void addActivity(ActivityVO activity);

    /**
     * 添加奖品配置集合
     * @param awardList 奖品配置集合
     */
    void addAward(List<AwardVO> awardList);


    /**
     * 添加策略配置
     * @param strategy 策略配置
     */
    void addStrategy(StrategyVO strategy);

    /**
     * 添加策略明细配置
     * @param strategyDetailList 策略明细集合
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    /**
     * 变换活动状态
     * @param activityId 活动id
     * @param beforeState 修改前状态
     * @param afterState 修改后状态
     * @return 更新结果
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState,Enum<Constants.ActivityState> afterState);

    /**
     * 查询活动账单信息
     * @param req 参与活动请求
     * @return    活动账单
     */
    ActivityBillVO queryActivityBill(PartakeReq req);

    /**
     * 扣减活动库存
     * @param activityId 活动id
     * @return           扣减结果
     */
    int subtractionActivityStock(Long activityId);

    /**
     * 扫描待处理的活动列表，状态位：通过、活动中
     * @param id ID
     * @return 待处理的活动列表
     */
    List<ActivityVO> scanToDoActivityList(Long id);

    /**
     *  扣减活动库存，通过Redis
     * @param uId 用户id
     * @param activityId 活动id
     * @param stockCount 总库存
     * @return 扣减结果
     */
    StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount);

    /**
     * 恢复活动库存，通过Redis 【如果非常异常，则需要进行缓存库存恢复，只保证不超卖的特性，所以不保证一定能恢复占用库存，另外最终可以由任务进行补偿库存】
     * @param activityId
     * @param tokenKey
     * @param code
     */
    void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code);
}
