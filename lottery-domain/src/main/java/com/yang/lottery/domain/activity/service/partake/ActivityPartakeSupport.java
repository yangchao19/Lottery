package com.yang.lottery.domain.activity.service.partake;

import com.yang.lottery.domain.activity.model.req.PartakeReq;
import com.yang.lottery.domain.activity.model.vo.ActivityBillVO;
import com.yang.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description: 活动领取模操作，一些通用的数据服务
 * @author：杨超
 * @date: 2023/4/9
 * @Copyright：
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req) {
        return activityRepository.queryActivityBill(req);
    }
}
