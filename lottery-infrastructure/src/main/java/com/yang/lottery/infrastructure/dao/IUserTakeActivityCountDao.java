package com.yang.lottery.infrastructure.dao;

import com.yang.db.router.annotation.DBRouter;
import com.yang.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户活动参与次数表Dao
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
@Mapper
public interface IUserTakeActivityCountDao {

    /**
     * 查询用户领取次数信息
     * @param userTakeActivityCountReq 请求入参【活动名，用户id】
     * @return 领取结果
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    /**
     * 插入领取次数信息
     * @param userTakeActivityCount 请求入参
     */
    @DBRouter
    void insert(UserTakeActivityCount userTakeActivityCount);


    /**
     * 更新领取次数信息 (剩余参加次数减一) left_count = left_count - 1;
     * @param userTakeActivityCount 请求入参
     * @return 更新数量
     */
    @DBRouter
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
