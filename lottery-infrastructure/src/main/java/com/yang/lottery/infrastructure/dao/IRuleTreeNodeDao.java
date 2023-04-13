package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
@Mapper
public interface IRuleTreeNodeDao {

    /**
     * 查询规则树节点
     * @param treeId 规则树id
     * @return       规则树节点集合
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点数
     * @param treeId 规则树id
     * @return       节点数量
     */
    int queryTreeNodeCount(Long treeId);

    /**
     * 查询规则树节点
     * @param treeId 规则树id
     * @return       节点集合
     */
    List<RuleTreeNode> queryTreeRulePoint(Long treeId);

}
