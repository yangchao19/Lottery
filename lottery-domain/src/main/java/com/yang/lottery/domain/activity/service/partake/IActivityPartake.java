package com.yang.lottery.domain.activity.service.partake;

import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.res.PartakeResult;
import com.yang.lottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.yang.lottery.domain.activity.model.vo.DrawOrderVO;
import com.yang.lottery.domain.activity.model.vo.InvoiceVO;

import java.util.List;

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

    /**
     * 更新发货单MQ状态
     * @param uId 用户id
     * @param orderId 订单id
     * @param mqState MQ 发送状态
     */
    void updateInvoiceMaState(String uId,Long orderId, Integer mqState);

    /**
     * 扫描发货单 MQ状态，把未发送MQ的单子扫描出来，做补偿
     * @param dbCount 指定分库
     * @param tbCount 指定分表
     * @return 发货单
     */
    List<InvoiceVO> scanInvoiceMqState(int dbCount, int tbCount);

    /**
     * 更新活动库存
     * @param activityPartakeRecordVO 活动领取记录
     */
    void updateActivityStock(ActivityPartakeRecordVO activityPartakeRecordVO);
}
