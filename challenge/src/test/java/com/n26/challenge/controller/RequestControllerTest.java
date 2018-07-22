package com.n26.challenge.controller;

import com.n26.challenge.request.Transaction;
import com.n26.challenge.response.Statistic;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestControllerTest.class);

    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

    @Test
    public void testMoreThanSixtySecondOldTransactions(){
        String uri="http://localhost:"+port+"/transactions";
        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity(uri, new Transaction(23.0,1478192204000L), Void.class);
        Assert.assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testWithInSixtySecondsTransactions(){
        String uri="http://localhost:"+port+"/transactions";
        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity(uri, new Transaction(23.0,System.currentTimeMillis()), Void.class);
        Assert.assertEquals( 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testStatusCodeStatistics(){
        String uri="http://localhost:"+port+"/statistics";
        ResponseEntity<Statistic> responseEntity = testRestTemplate.getForEntity(uri, Statistic.class);
        Assert.assertEquals( 200, responseEntity.getStatusCodeValue());

    }

    @Test
    public void testStatistics(){
        String uri="http://localhost:"+port+"/transactions";
        testRestTemplate.postForEntity(uri, new Transaction(25.0,System.currentTimeMillis()), Void.class);
        testRestTemplate.postForEntity(uri, new Transaction(75.0,System.currentTimeMillis()), Void.class);
        uri="http://localhost:"+port+"/statistics";
        ResponseEntity<Statistic> responseEntity = testRestTemplate.getForEntity(uri, Statistic.class);
        Assert.assertNotNull(responseEntity);
        Statistic statistic = responseEntity.getBody();
        Assert.assertEquals(50.0, statistic.getAvg(), 0);
        Assert.assertEquals(2, statistic.getCount().longValue());
        Assert.assertEquals(75.0, statistic.getMax(), 0);
        Assert.assertEquals(25.0, statistic.getMin(), 0);
        Assert.assertEquals(100.0, statistic.getSum(), 0);

        try {
            LOGGER.info("sleeping for 60 second, please wait......");
            Thread.sleep(60*1000);
            responseEntity = testRestTemplate.getForEntity(uri, Statistic.class);
            Assert.assertNotNull(responseEntity);
            statistic = responseEntity.getBody();
            Assert.assertEquals(0, statistic.getCount().longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
