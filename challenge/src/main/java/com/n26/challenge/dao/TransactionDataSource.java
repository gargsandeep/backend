package com.n26.challenge.dao;

import com.n26.challenge.request.Transaction;

import java.util.Collection;

public interface TransactionDataSource {
    void addTransaction(Transaction transaction);
    Collection<Transaction> getAllTxn();
}
