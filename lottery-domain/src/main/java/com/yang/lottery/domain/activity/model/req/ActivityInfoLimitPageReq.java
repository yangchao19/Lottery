package com.yang.lottery.domain.activity.model.req;

import com.yang.lottery.common.PageRequest;

/**
 * @description: 活动分页查询请求对象
 * @author：杨超
 * @date: 2023/5/16
 * @Copyright：
 */
public class ActivityInfoLimitPageReq extends PageRequest {

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    public ActivityInfoLimitPageReq(int page, int rows) {
        super(page, rows);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
