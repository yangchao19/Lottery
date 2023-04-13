package com.yang.lottery.infrastructure.repository;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.yang.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.yang.lottery.domain.rule.model.vo.TreeNodeVO;
import com.yang.lottery.domain.rule.model.vo.TreeRootVO;
import com.yang.lottery.domain.rule.repository.IRuleRepository;
import com.yang.lottery.infrastructure.dao.IRuleTreeDao;
import com.yang.lottery.infrastructure.dao.IRuleTreeNodeDao;
import com.yang.lottery.infrastructure.dao.IRuleTreeNodeLineDao;
import com.yang.lottery.infrastructure.po.RuleTree;
import com.yang.lottery.infrastructure.po.RuleTreeNode;
import com.yang.lottery.infrastructure.po.RuleTreeNodeLine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 规则信息仓储服务
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private IRuleTreeDao ruleTreeDao;

    @Resource
    private IRuleTreeNodeDao ruleTreeNodeDao;

    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {

        /** 规则树*/
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId(treeId);
        TreeRootVO treeRoot = new TreeRootVO();
        treeRoot.setTreeId(ruleTree.getId());
        treeRoot.setTreeRootNodeId(ruleTree.getTreeRootNodeId());

        /** 树节点 -> 树练接线*/
        Map<Long, TreeNodeVO> treeNodeMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodeList = ruleTreeNodeDao.queryRuleTreeNodeList(treeId);
        for (RuleTreeNode ruleTreeNode : ruleTreeNodeList) {


            List<TreeNodeLineVO> treeNodeLineVOList = new ArrayList<>();
            if (Constants.NodeType.STEM.equals(ruleTreeNode.getNodeType())) {

                RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();

                ruleTreeNodeLineReq.setTreeId(ruleTreeNode.getTreeId());
                ruleTreeNodeLineReq.setNodeIdFrom(ruleTreeNode.getId());
                List<RuleTreeNodeLine> ruleTreeNodeLineList = ruleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                //获取from当前非果实节点的节点列表
                for (RuleTreeNodeLine ruleTreeNodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeLineVO = new TreeNodeLineVO();
                    treeNodeLineVO.setNodeIdFrom(ruleTreeNodeLine.getNodeIdFrom());
                    treeNodeLineVO.setNodeIdTo(ruleTreeNodeLine.getNodeIdTo());
                    treeNodeLineVO.setRuleLimitType(ruleTreeNodeLine.getRuleLimitType());
                    treeNodeLineVO.setRuleLimitValue(ruleTreeNodeLine.getRuleLimitValue());
                    treeNodeLineVOList.add(treeNodeLineVO);
                }
            }

            TreeNodeVO treeNodeVO = new TreeNodeVO();
            treeNodeVO.setTreeId(ruleTreeNode.getTreeId());
            treeNodeVO.setTreeNodeId(ruleTreeNode.getId());
            treeNodeVO.setNodeType(ruleTreeNode.getNodeType());
            treeNodeVO.setNodeValue(ruleTreeNode.getNodeValue());
            treeNodeVO.setRuleKey(ruleTreeNode.getRuleKey());
            treeNodeVO.setRuleDesc(ruleTreeNode.getRuleDesc());
            treeNodeVO.setTreeNodeLineInfoList(treeNodeLineVOList);

            treeNodeMap.put(ruleTreeNode.getId(),treeNodeVO);
        }

        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRoot(treeRoot);
        treeRuleRich.setTreeNodeMap(treeNodeMap);


        return treeRuleRich;
    }
}
