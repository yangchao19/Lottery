package com.yang.lottery.application.process.deploy.impl;

import com.yang.lottery.application.process.deploy.IActivityDeployProcess;
import com.yang.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import com.yang.lottery.domain.activity.model.req.ActivityInfoLimitPageReq;
import com.yang.lottery.domain.activity.service.deploy.IActivityDeploy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 活动部署实现
 * @author：杨超
 * @date: 2023/5/17
 * @Copyright：
 */
@Service
public class ActivityDeployProcessImpl implements IActivityDeployProcess {

    @Resource
    private IActivityDeploy activityDeploy;

    @Override
    public ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req) {
        return activityDeploy.queryActivityInfoLimitPage(req);
    }
}
