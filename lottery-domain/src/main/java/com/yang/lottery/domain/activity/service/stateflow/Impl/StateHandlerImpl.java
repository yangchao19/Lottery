package com.yang.lottery.domain.activity.service.stateflow.Impl;

import com.yang.lottery.common.Constants;
import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.service.stateflow.IStateHandler;
import com.yang.lottery.domain.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/3
 * @Copyright：
 */
@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).arraignment(activityId,currentStatus);
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkPass(activityId,currentStatus);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkRefuse(activityId,currentStatus);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkRevoke(activityId,currentStatus);
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).close(activityId,currentStatus);
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).open(activityId,currentStatus);
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).doing(activityId,currentStatus);
    }
}
