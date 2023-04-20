package com.yang.lottery.infrastructure.dao;

import com.yang.db.router.annotation.DBRouter;
import com.yang.db.router.annotation.DBRouterStrategy;
import com.yang.lottery.infrastructure.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @description:
 * @author：杨超
 * @date: 2023/4/6
 * @Copyright：
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userStrategyExport 用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);


    /**
     * 查询数据
     * @param uId 用户id
     * @return 用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);

    /**
     * 更新发奖状态
     * @param userStrategyExport 发奖信息
     */
    @DBRouter
    void updateUserAwardState(UserStrategyExport userStrategyExport);

    /**
     * 更新发送消息状态
     * @param userStrategyExport 发送消息
     */
    @DBRouter
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);

    /**
     * 扫描发货单 MQ状态 ，把未发送MQ的单子扫描出来，做补偿
     * @return 发货单
     */
    List<UserStrategyExport> scanInvoiceMqState();
}

