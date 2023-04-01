package com.yang.lottery.domain.award.service.goods.impl;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.award.model.req.GoodsReq;
import com.yang.lottery.domain.award.model.res.DistributionRes;
import com.yang.lottery.domain.award.service.goods.DistributionBase;
import com.yang.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @description: 兑换码奖品
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
@Component
public class RedeemCodeGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 模拟调用兑换码发放接口
        logger.info("模拟调用兑换码发放接口 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        super.updateUserAwardState(req.getuId(),req.getOrderId(),req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.RedeemCodeGoods.getCode();
    }
}
