package com.n26.challenge.request;

public class Transaction implements Comparable<Transaction> {

    Double amount;
    Long timestamp;

    public Transaction() {
    }

    public Transaction(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "[amount=" + amount + ", timestamp=" + timestamp+"]" ;
    }

    @Override
    public int compareTo(Transaction txn) {
        if((this.getTimestamp() - txn.getTimestamp()) == 0)
            return 1;
        return -(int) (this.getTimestamp() - txn.getTimestamp());
    }
}
