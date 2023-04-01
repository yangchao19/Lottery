package com.yang.lottery.domain.strategy.model.aggregates;

import com.yang.lottery.infrastructure.po.Strategy;
import com.yang.lottery.infrastructure.po.StrategyDetail;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:抽奖策略相关所有信息
 * @author yc
 */
public class StrategyRich {
    private Long StrategyId;

    private Strategy strategy;

    private List<StrategyDetail> strategyDetailList;

    public StrategyRich() {}

    public StrategyRich(Long strategyId, Strategy strategy, List<StrategyDetail> strategyDetailList) {
        StrategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return StrategyId;
    }

    public void setStrategyId(Long strategyId) {
        StrategyId = strategyId;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetail> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetail> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}
