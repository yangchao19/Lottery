package com.yang.lottery.domain.activity.model.aggregates;

import com.yang.lottery.domain.activity.model.vo.ActivityVO;

import java.util.List;

/**
 * @description: 活动分页查询聚合对象
 * @author：杨超
 * @date: 2023/5/16
 * @Copyright：
 */
public class ActivityInfoLimitPageRich {

    private Long count;
    private List<ActivityVO> activityVOList;

    public ActivityInfoLimitPageRich() {
    }

    public ActivityInfoLimitPageRich(Long count, List<ActivityVO> activityVOList) {
        this.count = count;
        this.activityVOList = activityVOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ActivityVO> getActivityVOList() {
        return activityVOList;
    }

    public void setActivityVOList(List<ActivityVO> activityVOList) {
        this.activityVOList = activityVOList;
    }
}
