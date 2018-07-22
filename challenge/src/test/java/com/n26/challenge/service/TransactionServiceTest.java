package com.n26.challenge.service;

import com.n26.challenge.request.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    @Test
    public void testAddTransactions(){
        boolean isSixtySecsOldTxn = transactionService.addTransaction(new Transaction(40.0, System.currentTimeMillis()));
        Assert.assertFalse(isSixtySecsOldTxn);
        isSixtySecsOldTxn = transactionService.addTransaction(new Transaction(30.0, 1478192204000L));
        Assert.assertTrue(isSixtySecsOldTxn);
    }
}
