package com.yang.lottery.domain.rule.service.engine;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.rule.model.res.EngineResult;
import com.yang.lottery.domain.rule.model.vo.TreeNodeVO;
import com.yang.lottery.domain.rule.model.vo.TreeRootVO;
import com.yang.lottery.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @description: 规则引擎基础类
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public abstract class EngineBase extends EngineConfig implements EngineFilter {

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎服务");
    }

    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich,DecisionMatterReq matterReq) {

        TreeRootVO treeRoot = treeRuleRich.getTreeRoot();
        Map<Long, TreeNodeVO> treeNodeMap = treeRuleRich.getTreeNodeMap();

        //规则树根id
        Long treeNodeId = treeRoot.getTreeRootNodeId();
        TreeNodeVO treeNodeVO = treeNodeMap.get(treeNodeId);

        while (Constants.NodeType.STEM.equals(treeNodeVO.getNodeType())) {
            String ruleKey = treeNodeVO.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(matterReq);
            Long nextNode = logicFilter.filter(matterValue, treeNodeVO.getTreeNodeLineInfoList());
            treeNodeVO = treeNodeMap.get(nextNode);
            logger.info("决策树引擎=> userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{} ",treeRoot.getTreeName(),matterReq.getuId(),matterReq.getTreeId(),treeNodeVO.getTreeNodeId(),ruleKey,matterValue);
        }

        return treeNodeVO;
    }
}
