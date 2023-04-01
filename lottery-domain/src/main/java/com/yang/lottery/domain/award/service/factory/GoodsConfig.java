package com.yang.lottery.domain.award.service.factory;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.award.service.goods.IDistributionGoods;
import com.yang.lottery.domain.award.service.goods.impl.CouponGoods;
import com.yang.lottery.domain.award.service.goods.impl.DescGoods;
import com.yang.lottery.domain.award.service.goods.impl.PhysicalGoods;
import com.yang.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
public class GoodsConfig {
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();
    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    /**
     * PostConstruct: Spring容器启动时执行
     */
    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.DESC.getCode(),descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(),redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(),couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
