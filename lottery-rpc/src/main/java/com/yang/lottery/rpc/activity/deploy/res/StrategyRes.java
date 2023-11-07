package com.yang.lottery.rpc.activity.deploy.res;

import com.yang.lottery.common.Result;
import com.yang.lottery.rpc.activity.deploy.dto.StrategyDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 活动策略详细查询结果
 * @author：杨超
 * @date: 2023/9/17
 * @Copyright：
 */
public class StrategyRes implements Serializable {

    private Result result;

    private List<StrategyDTO> strategyList;

    public StrategyRes() {
    }

    public StrategyRes(Result result) {
        this.result = result;
    }

    public StrategyRes(Result result, List<StrategyDTO> strategyList) {
        this.result = result;
        this.strategyList = strategyList;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<StrategyDTO> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<StrategyDTO> strategyList) {
        this.strategyList = strategyList;
    }
}
