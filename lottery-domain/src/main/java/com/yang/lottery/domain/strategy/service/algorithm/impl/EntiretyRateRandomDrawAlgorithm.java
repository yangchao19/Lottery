package com.yang.lottery.domain.strategy.service.algorithm.impl;

import com.yang.lottery.domain.strategy.model.vo.AwardRateVO;
import com.yang.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:必中奖策略，当一个奖品被抽完时，将抽到该奖品的概率按比例分配给其他奖品
 * @author yc
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        //记录更新后的概率总和
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //排除不在抽奖范围的奖品id集合
        List<AwardRateVO> differenceAwardRateList = new ArrayList<>();

        //获取该抽奖策略的奖品概率元组
        List<AwardRateVO> awardRateInterValList = awardRateInfoMap.get(strategyId);

        //将在抽奖范围内的奖品加入到 differenceAwardRateList 中，
        //并且将累加他们的概率总和
        for (AwardRateVO awardRateVO : awardRateInterValList) {
            String awardId = awardRateVO.getAwardId();
            if(excludeAwardIds.contains(awardId)) {
                continue;
            }
            differenceAwardRateList.add(awardRateVO);
            differenceDenominator = differenceDenominator.add(awardRateVO.getAwardRate());
        }

        //前置判断:可供抽奖的奖品列表大小为0，返回NULL
        if (differenceAwardRateList.size() == 0) {
            return "";
        }
        //前置判断:可供抽奖的奖品列表为1，直接返回
        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        //获取1 -100 之间的随机数
        int randomVal = this.generateSecureRandomIntCode(100);

        //循环获取奖品
        String awardId = null;
        int cursorVal = 0;
        for (AwardRateVO awardRateVO : differenceAwardRateList) {
            int newRateVal = awardRateVO.getAwardRate().divide(differenceDenominator,2,BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if(randomVal <= (cursorVal + newRateVal)) {
                awardId = awardRateVO.getAwardId();
                break;
            }
            cursorVal += newRateVal;
        }

        return awardId;
    }
}
