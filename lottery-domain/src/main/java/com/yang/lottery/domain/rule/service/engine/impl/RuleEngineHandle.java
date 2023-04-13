package com.yang.lottery.domain.rule.service.engine.impl;

import com.yang.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.rule.model.res.EngineResult;
import com.yang.lottery.domain.rule.model.vo.TreeNodeVO;
import com.yang.lottery.domain.rule.repository.IRuleRepository;
import com.yang.lottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
@Service("ruleEngineHandle")
public class RuleEngineHandle extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        //决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());

        if (null == treeRuleRich) {
            throw new RuntimeException("Tree is null !");
        }

        //决策节点
        TreeNodeVO treeNodeVO = engineDecisionMaker(treeRuleRich, matter);

        //决策结果
        return  new EngineResult(matter.getuId(),treeNodeVO.getTreeId(),treeNodeVO.getTreeNodeId(),treeNodeVO.getNodeValue());

    }
}
