package com.yang.lottery.domain.activity.model.vo;


import java.util.Date;
import java.util.List;

/**
 * @description: 策略信息配置
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public class StrategyVO {
    /**
     * 策略id
     */
    private Long strategyId;
    /**
     * 策略模式
     */
    private Integer strategyMode;
    /**
     * 策略描述
     */
    private String strategyDesc;
    /**
     * 发奖方式【1.即时 2.定时 3.人工】
     */
    private Integer grantType;
    /**
     * 发奖时间
     */
    private Date grantDate;
    /**
     * 拓展信息
     */
    private String extInfo;
    /**
     * 策略详细配置
     */
    private List<StrategyDetailVO> strategyDetailList;

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public List<StrategyDetailVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }

    @Override
    public String toString() {
        return "StrategyVO{" +
                "strategyId='" + strategyId + '\'' +
                ", strategyMode=" + strategyMode +
                ", strategyDesc='" + strategyDesc + '\'' +
                ", grantType=" + grantType +
                ", grantDate=" + grantDate +
                ", extInfo='" + extInfo + '\'' +
                ", strategyDetailList=" + strategyDetailList +
                '}';
    }
}
