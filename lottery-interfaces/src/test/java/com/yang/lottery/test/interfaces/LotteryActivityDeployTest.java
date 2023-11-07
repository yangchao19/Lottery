package com.yang.lottery.test.interfaces;

import com.yang.lottery.common.Result;
import com.yang.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.yang.lottery.rpc.activity.deploy.ILotteryActivityDeploy;
import com.yang.lottery.rpc.activity.deploy.dto.StrategyDTO;
import com.yang.lottery.rpc.activity.deploy.res.StrategyRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：杨超
 * @date: 2023/9/17
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityDeployTest {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityDeployTest.class);

    @Resource
    private ILotteryActivityDeploy lotteryActivityDeploy;

    @Test
    public void test_strategy() {
        StrategyRes strategyRes = lotteryActivityDeploy.queryStrategyDetailByActivityId(10001L);
        Result result = strategyRes.getResult();
        logger.info("result code:{},info:{}",result.getCode(),result.getInfo());
        List<StrategyDTO> strategyList = strategyRes.getStrategyList();

        for (StrategyDTO strategyDTO : strategyList) {
            logger.info(strategyDTO.toString());
        }
    }


}
