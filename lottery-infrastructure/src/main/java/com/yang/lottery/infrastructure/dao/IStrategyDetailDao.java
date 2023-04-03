package com.yang.lottery.infrastructure.dao;


import com.yang.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 * @author yc
 */
@Mapper
public interface IStrategyDetailDao {

    /**
     * 查询策略表详细配置
     * @param strategyId 策略ID
     * @return           返回结果
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询无库存策略奖品ID
     * @param strategyId 策略ID
     * @return           返回结果
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetailReq 策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetailReq);

    /**
     * 插入策略明细列表
     * @param list 策略明细列表
     */
    void insertList(List<StrategyDetail> list);
}
