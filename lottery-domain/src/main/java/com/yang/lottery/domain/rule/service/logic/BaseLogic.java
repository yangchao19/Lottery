package com.yang.lottery.domain.rule.service.logic;

import com.sun.org.apache.xpath.internal.operations.Gt;
import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.yang.lottery.common.Constants;

import java.util.List;

/**
 * @description: 规则基础抽象类
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public abstract class BaseLogic implements LogicFilter{


    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        //选出合适的决策树的id
        for (TreeNodeLineVO nodeLine : treeNodeLineInfoList) {
            if (decisionLogic(matterValue,nodeLine)) {
                return nodeLine.getNodeIdTo();
            }
        }
        return Constants.Global.TREE_NULL_NODE;

    }

    /**
     * 获取规则比对值 (把比对值转化为字符串)
     * @param decisionMatter    决策物料
     * @return 比对值
     */
    @Override
    public abstract String matterValue(DecisionMatterReq decisionMatter);

    private  boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }
}
