package com.yang.lottery.domain.activity.service.deploy.impl;

import com.yang.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.yang.lottery.domain.activity.model.req.ActivityConfigReq;
import com.yang.lottery.domain.activity.model.vo.ActivityVO;
import com.yang.lottery.domain.activity.model.vo.AwardVO;
import com.yang.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.yang.lottery.domain.activity.model.vo.StrategyVO;
import com.yang.lottery.domain.activity.repository.IActivityRepository;
import com.yang.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    IActivityRepository activityRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void creatActivity(ActivityConfigReq req) {

        logger.info("创建活动配置开始，activityId:{}",req.getActivityId());

        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        try {
            //添加活动配置
            ActivityVO activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            //添加奖品配置
            List<AwardVO> awardList = activityConfigRich.getAwardList();
            activityRepository.addAward(awardList);

            //添加策略配偶直接
            StrategyVO strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            //添加策略明细配置
            List<StrategyDetailVO> strategyDetailList = strategy.getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailList);

            logger.info("创建活动配置完成,activityId:{}",req.getActivityId());
        } catch (DuplicateKeyException e) {
            logger.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}",req.getActivityId(),JSON.toJSONString(req),e);
           throw e;
        }
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        // TODO:非核心功能后续补充
    }
}
