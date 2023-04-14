package com.yang.lottery.domain.strategy.service.draw;

import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.yang.lottery.domain.strategy.model.req.DrawReq;
import com.yang.lottery.domain.strategy.model.res.DrawResult;
import com.yang.lottery.domain.strategy.model.vo.*;
import com.yang.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.yang.lottery.domain.strategy.service.draw.DrawConfig.drawAlgorithmMap;

/**
 * @description:定义抽象抽奖过程，模板模式
 * @author：杨超
 * @date: 2023/3/30
 * @Copyright：
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{
    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        //1.获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        //2.检验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(),strategyRich.getStrategyDetailList());

        //3.获取不在抽奖范围的列表，包括：奖品库存为空、风控策略、零时调整
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        //4.执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMap.get(strategy.getStrategyMode()), excludeAwardIds);
        return  buildDrawResult(req.getuId(),req.getStrategyId(),awardId);

    }

    /**
     * 获取不在抽奖范围的列表，包括奖品库存为空、风控策略、零时调整
     *
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);


    /**
     * 执行抽奖算法
     *
     * @param strategyId 策略ID
     * @param drawAlgorithm 抽奖算法模型
     * @param excludeAwardIds 排除奖品ID
     * @return 中奖奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm,List<String> excludeAwardIds);

    protected void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {

        //非单项概率，不必存入缓存
//        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
//            return;
//        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        //如果已经初始化过数据，不必重复初始化数据
        if (drawAlgorithm.isExistRateTuple(strategyId)) {
            return;
        }

        //解析并初始化中奖概率数据到散列表
        List<AwardRateVO> awardRateVOList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateVOList.add(new AwardRateVO(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, awardRateVOList);
    }


    /**
     * 包装抽奖结果
     *
     * @param uId        用户ID
     * @param strategyId 策略ID
     * @param awardId    奖品ID，null 情况：并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
     * @return 中奖结果
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId) {
        if (null == awardId) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardVO drawAwardVO = new DrawAwardVO(uId,award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardVO);
    }
}
