package com.yang.lottery.domain.rule.service.logic.impl;

import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
@Component
public class UserAgeFilter extends BaseLogic {
    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }
}
