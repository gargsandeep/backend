package com.n26.challenge.dao;

import com.n26.challenge.request.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class TransactionDataSourceImpl implements TransactionDataSource {

    Collection<Transaction> transactionCollection;

    TransactionDataSourceImpl(){
        transactionCollection = new ConcurrentSkipListSet<>();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        this.transactionCollection.add(transaction);
    }

    @Override
    public Collection<Transaction> getAllTxn() {
        return this.transactionCollection;
    }
}
