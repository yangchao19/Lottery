package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
@Mapper
public interface IRuleTreeNodeLineDao {

    /**
     * 查询规则树节点连线集合
     * @param req 入参
     * @return    规则树节点连线集合
     */
    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine req);

    /**
     * 查询规则树连线数量
     * @param treeId 规则树id
     * @return       规则树连线数量
     */
    int queryTreeNodeLineCount(Long treeId);
}
