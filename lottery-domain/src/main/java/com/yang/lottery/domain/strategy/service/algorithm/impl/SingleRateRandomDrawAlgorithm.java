package com.yang.lottery.domain.strategy.service.algorithm.impl;

import com.yang.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 * @author yc
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        //获取策略id对应的抽奖改路元组
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        //获取随机数对应的索引
        int randomVal = new SecureRandom().nextInt(100) + 1;
        int idx = super.hashIdx(randomVal);


        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)) {
            return "未中奖";
        }

        return awardId;
    }
}
