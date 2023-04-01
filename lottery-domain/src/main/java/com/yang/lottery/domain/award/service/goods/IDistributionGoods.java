package com.yang.lottery.domain.award.service.goods;

import com.yang.lottery.domain.award.model.req.GoodsReq;
import com.yang.lottery.domain.award.model.res.DistributionRes;

/**
 * @description: 抽奖，抽象出配送货物接口，把各类奖品模拟成货物、配送代表着发货，包括虚拟奖品和实物奖品
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
public interface IDistributionGoods {
    /**
     * 奖品配送接口，奖品类型 （1.文字描述 description 2.兑换码 redeemCode 3.优惠券 coupon 4.实物奖品 physical）
     * @param req 物品信息
     * @return 配送结果
     */
    DistributionRes doDistribution(GoodsReq req);

    /**
     * 获取商品类型信息
     * @return
     */
    Integer getDistributionGoodsName();
}
