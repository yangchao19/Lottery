package com.yang.lottery.domain.strategy.model.aggregates;

import com.yang.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.yang.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;

import java.util.List;

/**
 * Date:2023/3/28
 * Author:YangChao
 * Description:抽奖策略相关所有信息
 * @author yc
 */
public class StrategyRich {
    private Long StrategyId;

    private StrategyBriefVO strategyBriefVO;

    private List<StrategyDetailBriefVO> strategyDetailBriefVOListlList;

    public StrategyRich() {}

    public StrategyRich(Long strategyId, StrategyBriefVO strategyBriefVO, List<StrategyDetailBriefVO> strategyDetailList) {
        StrategyId = strategyId;
        this.strategyBriefVO = strategyBriefVO;
        this.strategyDetailBriefVOListlList = strategyDetailList;
    }

    public Long getStrategyId() {
        return StrategyId;
    }

    public void setStrategyId(Long strategyId) {
        StrategyId = strategyId;
    }

    public StrategyBriefVO getStrategy() {
        return strategyBriefVO;
    }

    public void setStrategy(StrategyBriefVO strategyBriefVO) {
        this.strategyBriefVO = strategyBriefVO;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailBriefVOListlList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailBriefVOListlList = strategyDetailList;
    }
}
