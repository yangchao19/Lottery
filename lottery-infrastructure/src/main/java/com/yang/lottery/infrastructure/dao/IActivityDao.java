package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Date:2023/3/26
 * Author:YangChao
 * Description:
 */
@Mapper
public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activityId);

}
