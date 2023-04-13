package com.yang.lottery.domain.rule.service.engine;

import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.rule.model.res.EngineResult;

/**
 * @description: 规则过滤器引擎
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public interface EngineFilter {

    /**
     * 规则过滤接口
     * @param matter    规则决策物料
     * @return          规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);
}
