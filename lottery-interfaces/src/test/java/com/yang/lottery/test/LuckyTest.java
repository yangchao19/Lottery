package com.yang.lottery.test;

import com.yang.lottery.rpc.activity.booth.ILotteryActivityBooth;
import com.yang.lottery.rpc.activity.booth.req.DrawReq;
import com.yang.lottery.rpc.activity.booth.res.DrawRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

/**
 * @description:
 * @author：杨超
 * @date: 2023/9/17
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckyTest {

    private Logger logger = LoggerFactory.getLogger(LuckyTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void  lottery() {
        DrawReq drawReq = new DrawReq();
        drawReq.setuId("vue_test_001");
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("code:{},info:{}",drawRes.getCode(),drawRes.getInfo());
    }
}
