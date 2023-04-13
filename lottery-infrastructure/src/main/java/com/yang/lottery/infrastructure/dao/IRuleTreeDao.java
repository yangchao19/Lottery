package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
@Mapper
public interface IRuleTreeDao {
    /**
     * 规则树查询
     * @param id Id
     * @return   规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树概要信息查询
     * @param treeId 规则树Id
     * @return 规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);
}
