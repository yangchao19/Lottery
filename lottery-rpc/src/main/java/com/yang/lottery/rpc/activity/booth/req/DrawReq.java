package com.yang.lottery.rpc.activity.booth.req;

import java.io.Serializable;

/**
 * @description: 抽奖请求
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public class DrawReq implements Serializable {

    private String uId;

    private Long activityId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long activityId) {
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
