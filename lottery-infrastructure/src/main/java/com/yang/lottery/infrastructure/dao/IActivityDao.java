package com.yang.lottery.infrastructure.dao;

import com.yang.lottery.domain.activity.model.vo.ActivityVO;
import com.yang.lottery.domain.activity.model.vo.AlterStateVO;
import com.yang.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Date:2023/3/26
 * Author:YangChao
 * Description:
 * @author yc
 */
@Mapper
public interface IActivityDao {
    /**
     * 插入活动配置
     * @param req 活动配置
     */
    void insert(Activity req);


    /**
     * 查询活动配置
     * @param activityId 活动id
     * @return
     */
    Activity queryActivityById(Long activityId);


    /**
     * 更新活动状态
     * @param alterStateVO 活动状态变更信息
     * @return
     */
    int alterState(AlterStateVO alterStateVO);


    /**
     * 扣减活动库存
     * @param activityId 活动ID
     * @return 更新数量
     */
    int subtractionActivityStock(Long activityId);


    /**
     * 查询正在活动或已通过的活动
     * @param id
     * @return
     */
    List<Activity> scanToDoActivityList(Long id);

    /**
     * 更新用户领取活动后，库存
     * @param activity 入参
     */
    void updateActivityStock(Activity activity);

}
