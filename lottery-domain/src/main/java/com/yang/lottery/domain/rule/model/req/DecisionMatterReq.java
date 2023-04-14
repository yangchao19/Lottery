package com.yang.lottery.domain.rule.model.req;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/12
 * @Copyright：
 */
public class DecisionMatterReq {

    /** 规则树id*/
    private Long treeId;

    /** 用户id*/
    private String uId;

    /** 决策值id*/
    private Map<String,Object> valMap;

    public DecisionMatterReq() {
    }

    public DecisionMatterReq(Long treeId, String uId, Map<String, Object> valMap) {
        this.treeId = treeId;
        this.uId = uId;
        this.valMap = valMap;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}
