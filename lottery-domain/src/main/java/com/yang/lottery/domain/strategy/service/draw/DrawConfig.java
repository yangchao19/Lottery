package com.yang.lottery.domain.strategy.service.draw;

import com.yang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date:2023/3/29
 * Author:YangChao
 * Description:
 */
public class DrawConfig {

    @Resource(name = "DefaultRateRandomDrawAlgorithm")
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource(name = "SingleRateRandomDrawAlgorithm")
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer,IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(1,defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(2,singleRateRandomDrawAlgorithm);
    }
}
