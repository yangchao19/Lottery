package com.yang.lottery.infrastructure.repository;

import com.yang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.yang.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.yang.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.yang.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.yang.lottery.domain.strategy.repository.IStrategyRepository;
import com.yang.lottery.infrastructure.dao.IAwardDao;
import com.yang.lottery.infrastructure.dao.IStrategyDao;
import com.yang.lottery.infrastructure.dao.IStrategyDetailDao;
import com.yang.lottery.infrastructure.po.Award;
import com.yang.lottery.infrastructure.po.Strategy;
import com.yang.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:
 * @author yc
 */
@Repository
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

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy,strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();

        for( StrategyDetail strategyDetail : strategyDetails) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail,strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }

        return new StrategyRich(strategyId,strategyBriefVO,strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);
        AwardBriefVO awardBriefVO = new AwardBriefVO();

        awardBriefVO.setAwardId(award.getAwardId());
        awardBriefVO.setAwardName(award.getAwardName());
        awardBriefVO.setAwardType(award.getAwardType());
        awardBriefVO.setAwardContent(award.getAwardContent());
        return awardBriefVO;
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
