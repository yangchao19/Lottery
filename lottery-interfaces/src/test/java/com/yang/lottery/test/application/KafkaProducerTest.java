package com.yang.lottery.test.application;

import com.yang.lottery.application.mq.producer.KafkaProducer;
import com.yang.lottery.common.Constants;
import com.yang.lottery.domain.activity.model.vo.InvoiceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.scanner.Constant;

import javax.annotation.Resource;


/**
 * @description:
 * @author：杨超
 * @date: 2023/4/14
 * @Copyright：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerTest {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void test_send() throws InterruptedException {
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setuId("xiaofuge");
        invoiceVO.setOrderId(1444540456057864192L);
        invoiceVO.setAwardId("3");
        invoiceVO.setAwardType(Constants.AwardType.DESC.getCode());
        invoiceVO.setAwardName("Code");
        invoiceVO.setAwardContent("苹果电脑");
        invoiceVO.setShippingAddress(null);
        invoiceVO.setExtInfo(null);

        kafkaProducer.sendLotteryInvoice(invoiceVO);

        while (true) {
            Thread.sleep(10000);
        }
    }
}
