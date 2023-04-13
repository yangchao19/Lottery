package com.yang.lottery.domain.rule.service.engine;

import com.yang.lottery.domain.rule.service.logic.LogicFilter;
import com.yang.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import com.yang.lottery.domain.rule.service.logic.impl.UserGenderFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 规则配置
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;
    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }

}
