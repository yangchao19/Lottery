package com.yang.lottery.rpc;

import com.yang.lottery.rpc.req.ActivityReq;
import com.yang.lottery.rpc.res.ActivityRes;

/**
 * Date:2023/3/26
 * Author:YangChao
 * Description:
 */
public interface IActivityBooth {
    ActivityRes queryActivityById(ActivityReq req);
}
