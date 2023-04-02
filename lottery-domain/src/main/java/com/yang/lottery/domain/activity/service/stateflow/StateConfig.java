package com.yang.lottery.domain.activity.service.stateflow;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/3
 * @Copyright：
 */
public class StateConfig {
    @Resource
    private ArraignmentState arraignmentState;
    @Resource
    private CloseState closeState;
    @Resource
    private DoingState doingState;
    @Resource
    private EditingState editingState;
    @Resource
    private OpenState openState;
    @Resource
    private PassState passState;
    @Resource
    private RefuseState refuseState;

    protected Map<Enum<Constants.ActivityState>,AbstractState> stateMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stateMap.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
        stateMap.put(Constants.ActivityState.CLOSE, closeState);
        stateMap.put(Constants.ActivityState.DOING, doingState);
        stateMap.put(Constants.ActivityState.EDIT, editingState);
        stateMap.put(Constants.ActivityState.OPEN, openState);
        stateMap.put(Constants.ActivityState.PASS, passState);
        stateMap.put(Constants.ActivityState.REFUSE, refuseState);
    }
}
