package com.yang.lottery.application.process.impl;

import com.yang.lottery.application.process.IActivityProcess;
import com.yang.lottery.application.process.req.DrawProcessReq;
import com.yang.lottery.application.process.res.DrawProcessResult;
import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.res.PartakeResult;
import com.yang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.yang.lottery.domain.activity.service.partake.IActivityPartake;
import com.yang.lottery.domain.strategy.model.req.DrawReq;
import com.yang.lottery.domain.strategy.model.res.DrawResult;
import com.yang.lottery.domain.strategy.model.vo.DrawAwardInfo;
import com.yang.lottery.domain.strategy.service.draw.IDrawExec;
import com.yang.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动抽奖流程编排
 * @author：杨超
 * @date: 2023/4/11
 * @Copyright：
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;


    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        //1 领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        //2 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(),strategyId, String.valueOf(takeId)));
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());

        }
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();

        //3 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req,strategyId,takeId,drawAwardInfo));

        //4 发送MQ,触发发奖流程

        //5 返回结果



        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(),drawAwardInfo);
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardInfo drawAwardInfo) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        return drawOrderVO;
    }
}
