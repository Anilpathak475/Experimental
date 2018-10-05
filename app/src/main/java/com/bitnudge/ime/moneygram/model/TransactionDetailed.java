package com.bitnudge.ime.moneygram.model;

import java.util.List;

public class TransactionDetailed {
    String duration;
    List<Transaction> transactions;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
