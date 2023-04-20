package com.yang.lottery.domain.award.service.goods.impl;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.award.model.req.GoodsReq;
import com.yang.lottery.domain.award.model.res.DistributionRes;
import com.yang.lottery.domain.award.service.goods.DistributionBase;
import com.yang.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @description: 优惠券奖品
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        //模拟调用优惠券发放接口
        logger.info("模拟调用优惠券发放接口 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        //更新用户领奖状态
        super.updateUserAwardState(req.getuId(),req.getOrderId(),req.getAwardId(), Constants.AwardState.SUCCESS.getCode());
        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(),Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.CouponGoods.getCode();
    }
}
