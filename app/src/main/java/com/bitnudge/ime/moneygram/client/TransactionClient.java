package com.bitnudge.ime.moneygram.client;

import com.bitnudge.ime.moneygram.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TransactionClient {
    @GET("/auth/api/v1/consumer/transaction/")
    Call<List<Transaction>> getTransactionHistory(@Header("Authorization") String token);
}
