package com.yang.lottery.test.application;

import com.alibaba.fastjson.JSON;
import com.yang.lottery.application.process.IActivityProcess;
import com.yang.lottery.application.process.req.DrawProcessReq;
import com.yang.lottery.application.process.res.DrawProcessResult;
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
 * @date: 2023/4/11
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityProcessTest {
    private Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess() {
        DrawProcessReq req = new DrawProcessReq();
        req.setuId("xiaofuge");
        req.setActivityId(100001L);
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(req);

        logger.info("请求入参：{}",JSON.toJSONString(req));
        logger.info("请求入参：{}", JSON.toJSONString(drawProcessResult));
    }

}
