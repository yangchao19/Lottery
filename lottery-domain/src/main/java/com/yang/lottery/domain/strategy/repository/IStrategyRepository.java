package com.yang.lottery.domain.strategy.repository;

import com.yang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.yang.lottery.domain.strategy.model.vo.AwardBriefVO;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 * @author yc
 */
public interface IStrategyRepository {
    /**
     * 查询策略对应的详细聚合数据
     * @param strategyId 策略id
     * @return
     */
    StrategyRich queryStrategyRich(Long strategyId);

    /**
     * 查询奖品信息
     * @param awardId 奖品id
     * @return award
     */
    AwardBriefVO queryAwardInfo(String awardId);

    /**
     * 查询奖品数量为空的奖品
     * @param strategyId 策略id
     * @return 数量为空的奖品list
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategy 策略ID
     * @param awardId 奖品ID
     * @return 扣减结果
     */
    boolean deductStock(Long strategy,String awardId);
}
