package com.yang.lottery.application.process.draw.req;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/11
 * @Copyright：
 */
public class DrawProcessReq {

    /**
     * 用户id
     */
    private String uId;

    /**
     * 活动id
     */
    private Long  activityId;

    public DrawProcessReq() {
    }

    public DrawProcessReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
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
}
