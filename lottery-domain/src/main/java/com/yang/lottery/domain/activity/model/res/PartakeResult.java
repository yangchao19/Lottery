package com.yang.lottery.domain.activity.model.res;

import com.yang.lottery.common.Result;

/**
 * @description: 活动参与结果
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
public class PartakeResult extends Result {
    private Long strategyId;

    private Long takeId;

    private Integer stockCount;

    private Integer stockSurplusCount;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
    }
}
