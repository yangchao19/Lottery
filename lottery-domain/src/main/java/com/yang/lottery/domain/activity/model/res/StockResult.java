package com.yang.lottery.domain.activity.model.res;

import com.yang.lottery.common.Result;

/**
 * @description: 库存处理结果
 * @author：杨超
 * @date: 2023/4/20
 * @Copyright：
 */
public class StockResult extends Result {
    /**
     *  库存 key
     */
    private String stockKey;
    /**
     *  activity 库存剩余
     */
    private Integer stockSurplusCount;

    public StockResult(String code, String info) {
        super(code, info);
    }

    public StockResult(String code, String info, String stockKey, Integer stockSurplusCount) {
        super(code, info);
        this.stockKey = stockKey;
        this.stockSurplusCount = stockSurplusCount;
    }

    public String getStockKey() {
        return stockKey;
    }

    public void setStockKey(String stockKey) {
        this.stockKey = stockKey;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
    }
}
