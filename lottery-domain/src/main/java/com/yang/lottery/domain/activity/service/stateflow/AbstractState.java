package com.yang.lottery.domain.activity.service.stateflow;

import com.yang.lottery.common.Constants;
import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public abstract class AbstractState {
    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 活动提审
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核通过
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核拒绝
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 撤销审核
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动关闭
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动开启
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动执行
     * @param activityId 活动id
     * @param currentState 当前状态
     * @return 执行结果
     */
    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currentState);

}
