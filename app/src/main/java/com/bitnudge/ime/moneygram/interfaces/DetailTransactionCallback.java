package com.bitnudge.ime.moneygram.interfaces;

import com.bitnudge.ime.moneygram.model.TransactionDetail;

public interface DetailTransactionCallback {
    void onSuccess(TransactionDetail transactionDetail);

    void onFailure(String error);
}
