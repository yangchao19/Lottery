package com.yang.lottery.infrastructure.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 */
public class StrategyDetail {
    private String id;
    private Long strategyId;
    private String awardId;
    private String awardCount;
    private BigDecimal awardRate;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(String awardCount) {
        this.awardCount = awardCount;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
