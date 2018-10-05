package com.bitnudge.ime.moneygram.client;

import com.bitnudge.ime.moneygram.model.PastReceivers;
import com.bitnudge.ime.moneygram.model.Recevicer;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.model.TransactionDetail;
import com.bitnudge.ime.moneygram.model.TransactionDetailed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TransactionClient {
    @GET("auth/api/v1/consumer/transaction/")
    Call<List<Transaction>> getTransactionHistory(@Header("Authorization") String token);

    @GET("auth/api/v1/consumer/receiver/")
    Call<Recevicer> getBeneficary(@Header("Authorization") String token);

    @GET("auth/api/v1/consumer/transactionDetail")
    Call<TransactionDetail> getDetailedTransaction(@Header("Authorization") String token, @Query("referenceNumber")int referenceNumber, @Query("transactionId") int transactionId,
                                                   @Query("lastName") String lastName, @Query("onlineTransaction") boolean onlineTransaction);
}
