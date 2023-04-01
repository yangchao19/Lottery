package com.yang.lottery.domain.award.service.factory;

import com.yang.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @description: 配送商品简单工厂，提供获取配送服务
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType) {
        return goodsMap.get(awardType);
    }
}
