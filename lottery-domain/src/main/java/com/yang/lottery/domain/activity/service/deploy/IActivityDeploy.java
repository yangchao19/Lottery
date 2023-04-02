package com.yang.lottery.domain.activity.service.deploy;

import com.yang.lottery.domain.activity.model.req.ActivityConfigReq;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public interface IActivityDeploy {
    /**
     * 创建活动
     * @param req 活动配置信息
     */
    void creatActivity(ActivityConfigReq req);

    /**
     * 更新活动信息
     * @param req 活动配置信息
     */
    void updateActivity(ActivityConfigReq req);
}
