package com.yang.lottery.rpc.activity.booth.req;

import java.util.Map;

/**
 * @description: 量化人群抽奖请求参数
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public class QuantificationDrawReq {
    /** 用户ID */
    private String uId;
    /** 规则树ID */
    private Long treeId;
    /** 决策值 */
    private Map<String, Object> valMap;

    public QuantificationDrawReq() {
    }

    public QuantificationDrawReq(String uId, Long treeId, Map<String, Object> valMap) {
        this.uId = uId;
        this.treeId = treeId;
        this.valMap = valMap;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}
