package com.yang.lottery.infrastructure.dao;


import com.yang.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 */
@Mapper
public interface IStrategyDetailDao {
    List<StrategyDetail> queryStrategyDetailById(Long strategyId);
}
