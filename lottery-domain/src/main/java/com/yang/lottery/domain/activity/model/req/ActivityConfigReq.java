package com.yang.lottery.domain.activity.model.req;

import com.yang.lottery.domain.activity.model.aggregates.ActivityConfigRich;

/**
 * @description: 活动配置请求对象
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public class ActivityConfigReq {
    /** 活动id*/
    private Long activityId;

    /** 活动配置信息*/
    private ActivityConfigRich activityConfigRich;

    public ActivityConfigReq() {
    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public ActivityConfigRich getActivityConfigRich() {
        return activityConfigRich;
    }

    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {
        this.activityConfigRich = activityConfigRich;
    }
}
