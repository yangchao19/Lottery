package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 */
@Mapper
public interface IStrategyDao {
    Strategy queryStrategyById(Long strategyId);
}
