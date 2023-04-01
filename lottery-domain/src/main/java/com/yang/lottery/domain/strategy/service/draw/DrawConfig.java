package com.yang.lottery.domain.strategy.service.draw;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date:2023/3/29
 * Author:YangChao
 * Description: 抽奖统一配置信息类
 * @author yc
 */
@Component
public class DrawConfig {

    @Resource(name = "entiretyRateRandomDrawAlgorithm")
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    @Resource(name = "singleRateRandomDrawAlgorithm")
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    /** 抽奖策略组 */
    protected static Map<Integer,IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(Constants.StrategyMode.ENTIRETY.getCode(),entiretyRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
    }
}
