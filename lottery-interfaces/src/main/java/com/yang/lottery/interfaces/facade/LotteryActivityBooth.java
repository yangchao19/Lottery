package com.yang.lottery.interfaces.facade;

import com.yang.lottery.application.process.draw.IActivityProcess;
import com.yang.lottery.application.process.draw.req.DrawProcessReq;
import com.yang.lottery.application.process.draw.res.DrawProcessResult;
import com.yang.lottery.application.process.draw.res.RuleQuantificationCrowdResult;
import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.rule.model.req.DecisionMatterReq;
import com.yang.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.yang.lottery.interfaces.assember.IMapping;
import com.yang.lottery.rpc.activity.booth.ILotteryActivityBooth;
import com.yang.lottery.rpc.activity.booth.dto.AwardDTO;
import com.yang.lottery.rpc.activity.booth.req.DrawReq;
import com.yang.lottery.rpc.activity.booth.req.QuantificationDrawReq;
import com.yang.lottery.rpc.activity.booth.res.DrawRes;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @description: 抽奖活动展台
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
@Service
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBooth.class);


    @Resource
    private IActivityProcess activityProcess;

    @Resource
    private IMapping<DrawAwardVO, AwardDTO> awardMapping;

    @Override
    public DrawRes doDraw(DrawReq drawReq) {

        try {
            logger.info("抽奖，开始 uId：{} activityId：{}",drawReq.getuId(),drawReq.getActivityId());

            //1.执行抽奖
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(drawReq.getuId(), drawReq.getActivityId()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("抽奖，失败（抽奖过程异常）uId：{} activityId:{}",drawReq.getuId(),drawReq.getActivityId());
                return new DrawRes(drawProcessResult.getCode(),drawProcessResult.getInfo());
            }

            //2.数据转换
            DrawAwardVO drawAwardVO = drawProcessResult.getDrawAwardVO();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(drawReq.getActivityId());

            //3.封装结果
            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("抽奖，完成 uid：{} activityId：{}",drawReq.getuId(),drawReq.getActivityId());
            return drawRes;
        } catch (Exception e) {
            logger.error("抽奖，失败 uId：{} activityId：{}",drawReq.getuId(),drawReq.getActivityId());
            return  new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {

        try {
            logger.info("量化人群抽奖，开始 uid：{}  treeId：{}",quantificationDrawReq.getuId(),quantificationDrawReq.getTreeId());


            //1.获取量化决策后的活动id
            RuleQuantificationCrowdResult ruleQuantificationCrowdResult = activityProcess.doRuleQuantificationCrowd(new DecisionMatterReq(quantificationDrawReq.getTreeId(),quantificationDrawReq.getuId(),quantificationDrawReq.getValMap()));

            //2.执行抽奖
            Long activityId = ruleQuantificationCrowdResult.getActivityId();
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(quantificationDrawReq.getuId(), activityId));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("量化人群抽奖，失败（规则引擎执行异常）uId：{} treeId：{}",quantificationDrawReq.getuId(),quantificationDrawReq.getTreeId());
                return new DrawRes(drawProcessResult.getCode(),drawProcessResult.getInfo());
            }

            //3.数据转换
            DrawAwardVO drawAwardVO = drawProcessResult.getDrawAwardVO();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(activityId);

            //4.封装结果
            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("量化人群抽奖，完成 uId：{} treeId：{} drawRes：{}",quantificationDrawReq.getuId(),quantificationDrawReq.getTreeId(),drawRes);

            return drawRes;
        } catch (Exception e) {
            logger.error("量化人群抽奖，失败 uId:{} treeId：{}",quantificationDrawReq.getuId(),quantificationDrawReq.getTreeId());
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }
}
