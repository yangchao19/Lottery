package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

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
    Award queryAwardInfo(String AwardId);
}
