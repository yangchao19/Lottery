package com.yang.lottery.test;

import com.alibaba.fastjson.JSON;

import com.yang.lottery.domain.strategy.model.res.DrawResult;
import com.yang.lottery.infrastructure.dao.IActivityDao;
import com.yang.lottery.infrastructure.po.Activity;
import com.yang.lottery.rpc.IActivityBooth;
import com.yang.lottery.rpc.req.ActivityReq;
import com.yang.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Date:2023/3/27
 * Author:YangChao
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Reference(interfaceClass = IActivityBooth.class, url = "dubbo://127.0.0.1:20880")
    private IActivityBooth activityBooth;


    @Resource
    IActivityDao activityDao;

    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(100002L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("xiaofuge");
        activityDao.insert(activity);
    }

    @Test
    public void test_rpc() {
        ActivityReq req = new ActivityReq();
        req.setActivityId(100002L);
        ActivityRes result = activityBooth.queryActivityById(req);
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }
    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }

}
