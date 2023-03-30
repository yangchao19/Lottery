package com.yang.lottery.domain.strategy.service.algorithm;

import com.yang.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:抽奖算法接口
 */

public interface IDrawAlgorithm {


    //
    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList);


    boolean isExistRateTuple(Long strategyId);


    String randomDraw(Long strategyId, List<String> excludeAwardIds);

}
