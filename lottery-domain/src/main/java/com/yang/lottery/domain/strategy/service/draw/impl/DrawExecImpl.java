package com.yang.lottery.domain.strategy.service.draw.impl;

import com.yang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.yang.lottery.domain.strategy.model.req.DrawReq;
import com.yang.lottery.domain.strategy.model.res.DrawResult;
import com.yang.lottery.domain.strategy.repository.IStrategyRepository;
import com.yang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.yang.lottery.domain.strategy.service.draw.DrawBase;
import com.yang.lottery.domain.strategy.service.draw.IDrawExec;
import com.yang.lottery.infrastructure.po.Award;
import com.yang.lottery.infrastructure.po.Strategy;
import com.yang.lottery.infrastructure.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Date:2023/3/29
 * Author:YangChao
 * Description:
 */
@Service("drawExec")
public class DrawExecImpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    //
    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq req) {

        logger.info("执行策略抽奖开始，strategyId：{}",req.getStrategyId());

        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();

        //校验和初始化数据
        checkAndInitRateData(req.getStrategyId(),strategy.getStrategyMode(),strategyDetailList);

        //根据策略模式抽奖
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());

        String awardId = drawAlgorithm.randomDraw(strategy.getStrategyId(), new ArrayList<>());

        Award award = strategyRepository.queryAwardInfo(awardId);

        logger.info("执策略抽奖完成，中奖用户：{} 奖品ID：{} 奖品名称：{}",req.getuId(),awardId,award.getAwardName());

        //封装结果
        return new DrawResult(req.getuId(),req.getStrategyId(),awardId,award.getAwardName());
    }
}
