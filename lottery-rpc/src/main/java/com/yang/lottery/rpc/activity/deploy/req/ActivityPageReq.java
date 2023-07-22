package com.yang.lottery.rpc.activity.deploy.req;

import com.yang.lottery.common.PageRequest;

import java.io.Serializable;

/**
 * @description:
 * @author：杨超
 * @date: 2023/5/16
 * @Copyright：
 */
public class ActivityPageReq extends PageRequest implements Serializable {
    /**
     * ERP ID,记录谁在操作
     */
    private String erpId;
    /**
     * 活动Id
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;

    public ActivityPageReq(int page, int rows) {
        super(page, rows);
    }
    public ActivityPageReq(String page, String rows) {
        super(page, rows);
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
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
