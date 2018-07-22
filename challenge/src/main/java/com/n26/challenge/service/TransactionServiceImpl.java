package com.n26.challenge.service;

import com.n26.challenge.dao.TransactionDataSource;
import com.n26.challenge.request.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDataSource transactionDataSource;

    @Override
    public boolean addTransaction(Transaction transaction) {
        if(System.currentTimeMillis()-transaction.getTimestamp() >= 60*1000)
            return true;
        transactionDataSource.addTransaction(transaction);
        return false;
    }
}
