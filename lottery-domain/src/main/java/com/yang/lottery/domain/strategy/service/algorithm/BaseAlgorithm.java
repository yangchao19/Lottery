package com.yang.lottery.domain.strategy.service.algorithm;

import com.yang.lottery.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:共用的算法逻辑
 * @author yc
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {

    /** 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647*/
    private final int HASH_INCREMENT = 0x61c88647;

    /** 数组初始化长度 128，保证数据填充时不发生碰撞的最小初始化值 */
    private final int RATE_TUPLE_LENGTH = 128;

    /** 存放概率与奖品对应的散列结果，strategyId -> rateTuple */
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    /**
     * 奖品区间概率值，strategyId -> [awardId->begin、awardId->end]
     */
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateVO> awardRateVOList) {
        // 保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateVOList);

        // 使用computeIfAbsent方法来获取rateTupleMap中指定strategyId对应的值。
        // 如果该值存在，则直接返回；如果不存在，则使用lambda表达式创建一个新的字符串数组，
        // 并将其放入rateTupleMap中。该字符串数组的长度是RATE_TUPLE_LENGTH。
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateVO awardRateVO : awardRateVOList) {
            int rateVal = awardRateVO.getAwardRate().multiply(new BigDecimal(100)).intValue();
            // 循环填充概率范围值
            // 将rateTuple[] 以（奖品概率对应的哈希值）为下标，设置为当前的奖品id
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                rateTuple[hashIdx(i)] = awardRateVO.getAwardId();
            }
            cursorVal += rateVal;
        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    /**
     * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
     *
     * @param val 值
     * @return 索引
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }
    protected int generateSecureRandomIntCode(int bound){
        return new SecureRandom().nextInt(bound) + 1;
    }
}
