package com.yang.lottery.domain.activity.repository;

import com.yang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.yang.lottery.domain.activity.model.vo.InvoiceVO;
import com.yang.lottery.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
public interface IUserTakeActivityRepository {
    /**
     * 扣减个人活动参与次数
     *
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @param partakeDate       领取时间
     * @return                  更新结果
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate);

    /**
     * 领取活动
     *  @param activityId        活动ID
     * @param activityName      活动名称
     * @param strategyId
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @param takeDate          领取时间
     * @param takeId            领取ID
     */
    void takeActivity(Long activityId, String activityName,Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId);

    /**
     * 锁定活动领取记录
     * @param uId         用户id
     * @param activityId  活动id
     * @param takeId      领取id
     * @return  更新结果
     */
    int lockTackActivity(String uId,Long activityId,Long takeId);


    /**
     * 保存抽奖信息
     * @param drawOrder 中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrder);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * @param activityId 活动id
     * @param uId        用户id
     * @return           领取单
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId,String uId);


    /**
     * 更新发货单MQ状态
     * @param uId 用户id
     * @param orderId 订单id
     * @param mqState MQ发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);

    /**
     * 扫描发货单 MQ状态，把未发送的MQ 的单子扫描出来，做补偿
     * @return 发货单
     */
    List<InvoiceVO> scanInvoiceMqState();
}
