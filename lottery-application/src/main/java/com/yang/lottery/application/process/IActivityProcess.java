package com.yang.lottery.application.process;

import com.yang.lottery.application.process.req.DrawProcessReq;
import com.yang.lottery.application.process.res.DrawProcessResult;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/11
 * @Copyright：
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖请求
     * @return    抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);
}
