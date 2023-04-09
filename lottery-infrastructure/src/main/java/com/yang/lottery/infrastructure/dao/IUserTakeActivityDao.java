package com.yang.lottery.infrastructure.dao;

import com.yang.db.router.annotation.DBRouter;
import com.yang.lottery.infrastructure.po.UserStrategyExport;
import com.yang.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/5
 * @Copyright：
 */
@Mapper
public interface IUserTakeActivityDao {
    /**
     * 插入用户领取活动信息
     *
     * @param userTakeActivity 入参
     */
    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

}
