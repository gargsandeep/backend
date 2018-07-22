package com.n26.challenge.service;

import com.n26.challenge.request.Transaction;

public interface TransactionService {
    boolean addTransaction(Transaction transaction);
}
