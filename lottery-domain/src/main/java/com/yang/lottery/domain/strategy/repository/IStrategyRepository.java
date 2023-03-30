package com.yang.lottery.domain.strategy.repository;

import com.yang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.yang.lottery.infrastructure.po.Award;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);
}
