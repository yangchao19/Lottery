package com.yang.lottery.domain.strategy.repository.impl;

import com.yang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.yang.lottery.domain.strategy.repository.IStrategyRepository;
import com.yang.lottery.infrastructure.dao.IAwardDao;
import com.yang.lottery.infrastructure.dao.IStrategyDao;
import com.yang.lottery.infrastructure.dao.IStrategyDetailDao;
import com.yang.lottery.infrastructure.po.Award;
import com.yang.lottery.infrastructure.po.Strategy;
import com.yang.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 * @author yc
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;


    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {

        Strategy strategy = strategyDao.queryStrategy(strategyId);

        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(strategyId);

        return new StrategyRich(strategyId,strategy,strategyDetails);
    }

    @Override
    public Award queryAwardInfo(String awardId) {

        return awardDao.queryAwardInfo(awardId);
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(req);

        return count == 1;
    }


}
