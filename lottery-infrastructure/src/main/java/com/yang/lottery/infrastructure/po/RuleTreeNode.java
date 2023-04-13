package com.yang.lottery.infrastructure.po;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
public class RuleTreeNode {

    /**主键id*/
    private Long id;

    /**规则树id*/
    private Long treeId;

    /**节点类型：1.子叶  2.果实*/
    private Integer nodeType;

    /**果实值*/
    private String nodeValue;

    /**规则key*/
    private String ruleKey;

    /**规则描述*/
    private String ruleDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}
