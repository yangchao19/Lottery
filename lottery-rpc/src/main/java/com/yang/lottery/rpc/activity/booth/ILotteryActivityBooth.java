package com.yang.lottery.rpc.activity.booth;

import com.yang.lottery.rpc.activity.booth.req.DrawReq;
import com.yang.lottery.rpc.activity.booth.req.QuantificationDrawReq;
import com.yang.lottery.rpc.activity.booth.res.DrawRes;

/**
 * @description: 抽奖活动展台接口
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     * @param drawReq   请求参数
     * @return          抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);


    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 请求参数
     * @return                      抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);
}
