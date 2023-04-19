package com.yang.lottery.application.worker;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.yang.lottery.common.Constants;
import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.vo.ActivityVO;
import com.yang.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.yang.lottery.domain.activity.service.stateflow.IStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @description: 抽奖业务，任务配置
 * @author：杨超
 * @date: 2023/4/18
 * @Copyright：
 */
@Component
public class LotteryXxlJob {
    Logger logger = LoggerFactory.getLogger(LotteryXxlJob.class);

    @Resource
    private IActivityDeploy activityDeploy;

    @Resource
    private IStateHandler stateHandler;


    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() throws Exception {
        logger.info("扫描活动状态 Begin");

        List<ActivityVO> activityVOList = activityDeploy.scanToDoActivityList(0L);
        if (activityVOList.isEmpty()) {
            logger.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
            return;
        }

        while (!activityVOList.isEmpty()) {
            for (ActivityVO activityVO : activityVOList) {
                Integer state = activityVO.getState();
                switch (state) {
                    case 4:
                        Result state4Result = stateHandler.doing(activityVO.getActivityId(), Constants.ActivityState.PASS);
                        logger.info("扫描活动状态为活动中 结果：{} activityId:{} activityName:{} creator:{}", JSON.toJSONString(state4Result), activityVO.getActivityId(),activityVO.getActivityName(),activityVO.getCreator());
                        break;
                    case 5:
                        if(activityVO.getEndDateTime().before(new Date())) {
                            Result state5Result = stateHandler.close(activityVO.getActivityId(), Constants.ActivityState.DOING);
                            logger.info("扫描活动状态为关闭 结果：{} activityId:{} activityName:{} creator:{}",JSON.toJSONString(state5Result),activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        }
                         break;
                    default:
                        break;
                }
            }

            ActivityVO activityVO = activityVOList.get(activityVOList.size() - 1);
            activityVOList = activityDeploy.scanToDoActivityList(activityVO.getId());
        }

        logger.info("扫描活动状态 End");
    }

}
