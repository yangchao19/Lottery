package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 * @author yc
 */
@Mapper
public interface IAwardDao {
    /**
     * 查询奖品信息
     *
     * @param awardId 奖品ID
     * @return        奖品信息
     */
    Award queryAwardInfo(String awardId);

    /**
     * 将奖品信息配置
     * @param list 奖品配置
     */
    void insertList(List<Award> list);
}
