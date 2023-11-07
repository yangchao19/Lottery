package com.yang.lottery.rpc.activity.deploy;

import com.yang.lottery.rpc.activity.deploy.req.ActivityPageReq;
import com.yang.lottery.rpc.activity.deploy.res.ActivityRes;
import com.yang.lottery.rpc.activity.deploy.res.StrategyRes;

/**
 * @description: 抽奖活动活动部署服务接口
 * @author：杨超
 * @date: 2023/5/16
 * @Copyright：
 */
public interface ILotteryActivityDeploy {
    /**
     * 通过分页查询活动列表信息，给ERP运营使用
     * @param req 查询参数
     * @return    查询结果
     */
    ActivityRes queryActivityListByPageForErp(ActivityPageReq req);


    /**
     * 通过活动id查询策略明细
     * @param activityId
     * @return
     */
    StrategyRes queryStrategyDetailByActivityId(Long activityId);
}
