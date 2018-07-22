package com.n26.challenge.service;

import com.n26.challenge.request.Transaction;
import com.n26.challenge.response.Statistic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticServiceTest.class);


    @Autowired
    StatisticService statisticService;

    @Autowired
    TransactionService transactionService;

    @Before
    public  void clearOldTxns(){
        try {
            LOGGER.info("sleeping for 60 second, please wait......");
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetStatistic(){
        transactionService.addTransaction(new Transaction(50.0, System.currentTimeMillis()));
        //old txn
        transactionService.addTransaction(new Transaction(100.0, 1478192204000L));
        transactionService.addTransaction(new Transaction(50.0, System.currentTimeMillis()));
        Statistic statistic = statisticService.getStatistics();
        Assert.assertNotNull(statistic);
        Assert.assertEquals(2, statistic.getCount().longValue());
        Assert.assertEquals(50, statistic.getAvg(), 0);
        Assert.assertEquals(50, statistic.getMax(), 0);
        Assert.assertEquals(50, statistic.getMin(), 0);
        Assert.assertEquals(100, statistic.getSum(), 0);
    }
}
