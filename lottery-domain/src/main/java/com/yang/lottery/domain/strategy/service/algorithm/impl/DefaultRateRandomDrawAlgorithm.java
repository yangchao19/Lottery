package com.yang.lottery.domain.strategy.service.algorithm.impl;

import com.yang.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.yang.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:必中奖策略，当一个奖品被抽完时，将抽到该奖品的概率按比例分配给其他奖品
 */
@Component("DefaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        //记录更新后的概率总和
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //排除不在抽奖范围的奖品id集合
        List<AwardRateInfo> differenceAwardRateList = new ArrayList<>();

        //获取该抽奖策略的奖品概率元组
        List<AwardRateInfo> awardRateInterValList = awardRateInfoMap.get(strategyId);

        //将在抽奖范围内的奖品加入到 differenceAwardRateList 中，
        //并且将累加他们的概率总和
        for (AwardRateInfo awardRateInfo : awardRateInterValList) {
            String awardId = awardRateInfo.getAwardId();
            if(excludeAwardIds.contains(awardId)) continue;
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        //前置判断
        if (differenceAwardRateList.size() == 0) return "";
        if (differenceAwardRateList.size() == 1) return differenceAwardRateList.get(0).getAwardId();

        //获取1 -100 之间的随机数
        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        //循环获取奖品
        String awardId = "";
        int cursorVal = 0;
        for (AwardRateInfo awardRateInfo : differenceAwardRateList) {
            int newRateVal = awardRateInfo.getAwardRate().divide(differenceDenominator,2,BigDecimal.ROUND_UP).intValue();
            if(randomVal <= (cursorVal + newRateVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += newRateVal;
        }

        return awardId;
    }
}
