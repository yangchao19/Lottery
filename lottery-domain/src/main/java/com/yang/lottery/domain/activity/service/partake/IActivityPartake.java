package com.yang.lottery.domain.activity.service.partake;

import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.res.PartakeResult;
import com.yang.lottery.domain.activity.model.vo.DrawOrderVO;

/**
 * @description: 抽奖活动参与接口
 * @author：杨超
 * @date: 2023/4/2
 * @Copyright：
 */
public interface IActivityPartake {

    /**
     * 参与活动
     * @param req 入参
     * @return 领取结果
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);
}
