package com.yang.lottery.application.process;

import com.yang.lottery.application.process.req.DrawProcessReq;
import com.yang.lottery.application.process.res.DrawProcessResult;
import com.yang.lottery.application.process.res.RuleQuantificationCrowdResult;
import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/11
 * @Copyright：
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖请求
     * @return    抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

    /**
     * 规则量化人群，返回可参与的活动id
     * @param req   规则请求
     * @return      量化结果，用户可以参与的活动id
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);
}
