package com.yang.lottery.rpc.activity.deploy.res;

import com.yang.lottery.common.Result;
import com.yang.lottery.rpc.activity.deploy.dto.ActivityDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/5/17
 * @Copyright：
 */
public class ActivityRes implements Serializable {
    private Result result;
    private Long count;
    private List<ActivityDTO> activityDTOList;

    public ActivityRes() {
    }

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, Long count, List<ActivityDTO> activityDTOList) {
        this.result = result;
        this.count = count;
        this.activityDTOList = activityDTOList;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ActivityDTO> getActivityDTOList() {
        return activityDTOList;
    }

    public void setActivityDTOList(List<ActivityDTO> activityDTOList) {
        this.activityDTOList = activityDTOList;
    }
}
