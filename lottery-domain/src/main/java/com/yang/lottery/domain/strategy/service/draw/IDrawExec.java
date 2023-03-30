package com.yang.lottery.domain.strategy.service.draw;

import com.yang.lottery.domain.strategy.model.req.DrawReq;
import com.yang.lottery.domain.strategy.model.res.DrawResult;

/**
 * Date:2023/3/29
 * Author:YangChao
 * Description:
 */
public interface IDrawExec {
    DrawResult doDrawExec(DrawReq req);
}
