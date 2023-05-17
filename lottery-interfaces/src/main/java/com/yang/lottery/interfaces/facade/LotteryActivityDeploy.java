package com.yang.lottery.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.yang.lottery.application.process.deploy.IActivityDeployProcess;
import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import com.yang.lottery.domain.activity.model.req.ActivityInfoLimitPageReq;
import com.yang.lottery.domain.activity.model.vo.ActivityVO;
import com.yang.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.yang.lottery.infrastructure.po.Activity;
import com.yang.lottery.interfaces.assember.IMapping;
import com.yang.lottery.rpc.activity.deploy.ILotteryActivityDeploy;
import com.yang.lottery.rpc.activity.deploy.dto.ActivityDTO;
import com.yang.lottery.rpc.activity.deploy.req.ActivityPageReq;
import com.yang.lottery.rpc.activity.deploy.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/29
 * @Copyright：
 */
@Service
public class LotteryActivityDeploy implements ILotteryActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityDeploy.class);

    @Resource
    private IActivityDeployProcess activityDeploy;

    @Resource
    private IMapping<ActivityVO, ActivityDTO> activityMapping;

    @Override
    public ActivityRes queryActivityListByPageForErp(ActivityPageReq req) {

        try {
            logger.info("活动部署分页数据查询开始 erpID：{}", req.getErpId());

            // 1. 包装入参
            ActivityInfoLimitPageReq activityInfoLimitPageReq = new ActivityInfoLimitPageReq(req.getPage(), req.getRows());
            activityInfoLimitPageReq.setActivityId(req.getActivityId());
            activityInfoLimitPageReq.setActivityName(req.getActivityName());

            // 2. 查询结果
            ActivityInfoLimitPageRich activityInfoLimitPageRich = activityDeploy.queryActivityInfoLimitPage(activityInfoLimitPageReq);
            Long count = activityInfoLimitPageRich.getCount();
            List<ActivityVO> activityVOList = activityInfoLimitPageRich.getActivityVOList();

            // 3. 转换对象
            List<ActivityDTO> activityDTOList = activityMapping.sourceToTarget(activityVOList);

            // 4. 封装数据
            ActivityRes activityRes = new ActivityRes(Result.buildSuccessResult());
            activityRes.setCount(count);
            activityRes.setActivityDTOList(activityDTOList);

            logger.info("活动部署分页数据查询完成 erpID：{} count：{}", req.getErpId(), count);

            // 5. 返回结果
            return activityRes;
        } catch (Exception e) {
            logger.error("活动部署分页数据查询失败 erpId：{} reqStr：{}",req.getErpId(), JSON.toJSON(req), e);
            return new ActivityRes(Result.buildErrorResult());
        }
    }
}
