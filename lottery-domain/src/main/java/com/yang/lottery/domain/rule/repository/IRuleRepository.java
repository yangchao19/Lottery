package com.yang.lottery.domain.rule.repository;

import com.yang.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
public interface IRuleRepository {


    /**
     * 查询规则决策树配置
     * @param treeId 决策树id
     * @return       决策树配置
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
