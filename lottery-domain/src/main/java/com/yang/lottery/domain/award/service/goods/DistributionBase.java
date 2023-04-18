package com.yang.lottery.domain.award.service.goods;

import com.yang.lottery.domain.award.repository.IAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IAwardRepository awardRepository;

    protected void updateUserAwardState(String uId,Long orderOd,String awardId,Integer awardState,String awardStateInfo) {
        //TODO 后期在分库分表中更新用户个人抽奖记录中中奖和奖品的分发状态
        logger.info("TODO 后期在分库分表中更新用户个人抽奖记录中中奖和奖品的分发状态 uId:{}",uId);
    }
}
