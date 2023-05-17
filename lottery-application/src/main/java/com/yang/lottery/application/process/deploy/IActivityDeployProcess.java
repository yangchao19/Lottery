package com.yang.lottery.application.process.deploy;

import com.yang.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import com.yang.lottery.domain.activity.model.req.ActivityInfoLimitPageReq;

/**
 * @description:
 * @author：杨超
 * @date: 2023/5/17
 * @Copyright：
 */
public interface IActivityDeployProcess {

    /**
     * 查询活动分页查询聚合对象
     * @param req 请求参数：分页、活动
     * @return    查询结果
     */
    ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req);
}
