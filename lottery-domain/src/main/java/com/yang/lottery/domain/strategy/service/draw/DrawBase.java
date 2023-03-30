package com.yang.lottery.domain.strategy.service.draw;

import com.yang.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.yang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.yang.lottery.infrastructure.po.Award;
import com.yang.lottery.infrastructure.po.Strategy;
import com.yang.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2023/3/29
 * Author:YangChao
 * Description:
 */
public class DrawBase extends DrawConfig{

    //如果不为编号1的抽奖策略，就初始化新的抽奖策略
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {
        if (1 != strategyMode) return;
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        //是否存在该策略id对应的概率分布表
        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);

        if (existRateTuple) return;

        //初始化对应的概率表
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId,awardRateInfoList);
     }
}
