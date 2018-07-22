package com.n26.challenge.service;

import com.n26.challenge.dao.TransactionDataSource;
import com.n26.challenge.request.Transaction;
import com.n26.challenge.response.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatisitcServiceImpl implements  StatisticService{

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisitcServiceImpl.class);

    @Autowired
    TransactionDataSource transactionDataSource;

    @Override
    public Statistic getStatistics() {
        Collection<Transaction> transactionCollection = transactionDataSource.getAllTxn();
        LOGGER.info("txn collections are {}", transactionCollection);
        double sum = 0,avg = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        long count=0;

        for(Transaction transaction : transactionCollection){
            if(System.currentTimeMillis() - transaction.getTimestamp() >= 60*1000)
                break;
            count++;
            sum = sum + transaction.getAmount();
            if(max < transaction.getAmount())
                max = transaction.getAmount();
            if(min > transaction.getAmount())
                min = transaction.getAmount();
        }
        avg = count == 0 ? 0 : sum/count;
        Statistic statistic = new Statistic(sum, avg, max, min, count);

        return statistic;
    }
}
