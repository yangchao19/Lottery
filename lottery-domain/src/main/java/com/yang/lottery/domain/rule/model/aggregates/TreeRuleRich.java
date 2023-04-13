package com.yang.lottery.domain.rule.model.aggregates;

import com.yang.lottery.domain.rule.model.vo.TreeNodeVO;
import com.yang.lottery.domain.rule.model.vo.TreeRootVO;

import java.util.Map;

/**
 * @description: 规则树集合
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
public class TreeRuleRich {

    /** 树根信息*/
    private TreeRootVO treeRoot;

    /** 树节点 Id -> 子节点 */
    private Map<Long, TreeNodeVO> treeNodeMap;

    public TreeRootVO getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRootVO treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNodeVO> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNodeVO> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }
}
