package com.yang.lottery.domain.activity.model.vo;

import javax.naming.InsufficientResourcesException;

/**
 * @description: 奖品信息配置
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public class AwardVO {
    /**
     * 奖品id
     */
    private String awardId;
    /**
     * 奖品类型
     */
    private Integer awardType;
    /**
     * 奖品名字
     */
    private String awardName;
    /**
     * 奖品内容描述
     */
    private String awardContent;

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    @Override
    public String toString() {
        return "AwardVO{" +
                "awardId='" + awardId + '\'' +
                ", awardType=" + awardType +
                ", awardName='" + awardName + '\'' +
                ", awardContent='" + awardContent + '\'' +
                '}';
    }
}
