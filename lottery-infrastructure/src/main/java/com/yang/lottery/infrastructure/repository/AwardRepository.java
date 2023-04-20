package com.yang.lottery.infrastructure.repository;

import com.yang.lottery.domain.award.repository.IAwardRepository;
import com.yang.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.yang.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
@Repository
public class AwardRepository implements IAwardRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;


    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardState(userStrategyExport);
    }
}
