package com.yang.lottery.application.process.draw.res;

import com.yang.lottery.common.Result;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public class RuleQuantificationCrowdResult extends Result {

    private Long activityId;


    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
