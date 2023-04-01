package com.yang.lottery.domain.strategy.service.draw;

import com.yang.lottery.domain.strategy.model.req.DrawReq;
import com.yang.lottery.domain.strategy.model.res.DrawResult;

/**
 * Date:2023/3/29
 * Author:YangChao
 * Description: 抽奖执行接口
 * @author yc
 */
public interface IDrawExec {
    /**
     * 抽奖方法
     * @param req 抽奖参数 ：用户ID、策略ID
     * @return 中奖结果
     */
    DrawResult doDrawExec(DrawReq req);
}
