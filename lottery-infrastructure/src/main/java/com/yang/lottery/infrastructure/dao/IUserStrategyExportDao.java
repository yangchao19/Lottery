package com.yang.lottery.infrastructure.dao;

import com.yang.db.router.annotation.DBRouter;
import com.yang.db.router.annotation.DBRouterStrategy;
import com.yang.lottery.infrastructure.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;


/**
 * @description:
 * @author：杨超
 * @date: 2023/4/6
 * @Copyright：
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);
}

