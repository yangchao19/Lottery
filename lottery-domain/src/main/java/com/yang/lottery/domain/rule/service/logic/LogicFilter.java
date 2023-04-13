package com.yang.lottery.domain.rule.service.logic;

import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @description: 规则过滤器接口
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点
     * @return                      下一个节点id
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);


    /**
     * 获取决策值
     * @param decisionMatter    决策物料
     * @return                  决策值
     */
    String matterValue(DecisionMatterReq decisionMatter);

}
