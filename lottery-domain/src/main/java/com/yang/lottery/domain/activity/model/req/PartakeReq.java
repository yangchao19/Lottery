package com.yang.lottery.domain.activity.model.req;

import java.util.Date;

/**
 * @description: 参与活动请求
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
public class PartakeReq {
    /**
     * 用户id
     */
    private String uId;
    /**
     * 活动id
     */
    private Long activityId;
    /**
     * 活动领取时间
     */
    private Date partakeDate;

    public PartakeReq() {
    }

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();
    }

    public PartakeReq(String uId, Long activityId, Date partakeDate) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Date getPartakeDate() {
        return partakeDate;
    }

    public void setPartakeDate(Date partakeDate) {
        this.partakeDate = partakeDate;
    }
}
