package com.yang.lottery.domain.award.model.req;

import com.yang.lottery.domain.award.model.vo.ShippingAddress;


/**
 * @description:
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
public class GoodsReq {
    /** 用户ID */
    private String uId;

    /** 抽奖单号*/
    private Long orderId;

    /** 奖品ID*/
    private String awardId;

    /** 奖品名称*/
    private String awardName;

    /** 奖品描述*/
    private String awardContent;

    /** 四级收货地址（只有实物需要地址）*/
    private ShippingAddress shippingAddress;

    /** 扩展信息，用于一些个性商品发放所需要的透传字段内容 */
    private String exInfo;

    public GoodsReq() {
    }

    /** 不需要收货地址*/
    public GoodsReq(String uId, Long orderId, String awardId, String awardName, String awardContent) {
        this.uId = uId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }

    /** 需要收货地址*/
    public GoodsReq(String uId, Long orderId, String awardId, String awardName, String awardContent, ShippingAddress shippingAddress) {
        this.uId = uId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.shippingAddress = shippingAddress;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
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

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getExInfo() {
        return exInfo;
    }

    public void setExInfo(String exInfo) {
        this.exInfo = exInfo;
    }
}
