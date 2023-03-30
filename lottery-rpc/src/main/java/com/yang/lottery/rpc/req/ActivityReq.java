package com.yang.lottery.rpc.req;

import java.io.Serializable;

/**
 * Date:2023/3/26
 * Author:YangChao
 * Description:
 */
public class ActivityReq implements Serializable {
    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
