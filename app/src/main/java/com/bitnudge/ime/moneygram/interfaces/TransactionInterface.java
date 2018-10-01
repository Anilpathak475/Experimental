package com.bitnudge.ime.moneygram.interfaces;

import com.bitnudge.ime.moneygram.model.Transaction;

import java.util.List;

public interface TransactionInterface {
    void onSuccess(List<Transaction> transactions);

    void onFailure(String error);
}
