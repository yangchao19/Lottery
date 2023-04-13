package com.yang.lottery.domain.rule.model.vo;

/**
 * @description: 规则树根配置类
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
public class TreeRootVO {

    /** 规则树id*/
    private Long treeId;

    /** 决策树根id*/
    private Long treeRootNodeId;

    /** 决策树名称*/
    private String treeName;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "TreeRootVO{" +
                "treeId=" + treeId +
                ", treeRootNodeId=" + treeRootNodeId +
                ", treeName='" + treeName + '\'' +
                '}';
    }
}
