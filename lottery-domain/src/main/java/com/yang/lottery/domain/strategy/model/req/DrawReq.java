package com.yang.lottery.domain.strategy.model.req;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:抽奖请求类
 */
public class DrawReq {
    //用户id
    private String uId;

    //策略id
    private Long strategyId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
