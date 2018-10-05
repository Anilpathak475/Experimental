package com.bitnudge.ime.moneygram.interfaces;

import com.bitnudge.ime.moneygram.model.Receiver;
import com.bitnudge.ime.moneygram.model.Recevicer;
import com.bitnudge.ime.moneygram.model.Transaction;

import java.util.List;

public interface ReceiverCallback {
    void onSuccess(Recevicer recevicer);

    void onFailure(String error);
}
