package com.bitnudge.ime.moneygram.store;

import com.bitnudge.ime.moneygram.client.TransactionClient;
import com.bitnudge.ime.moneygram.interfaces.DetailTransactionCallback;
import com.bitnudge.ime.moneygram.interfaces.ReceiverCallback;
import com.bitnudge.ime.moneygram.interfaces.TransactionInterface;
import com.bitnudge.ime.moneygram.model.Recevicer;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.model.TransactionDetail;
import com.bitnudge.ime.moneygram.network.ClientGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionStore {
    private ClientGenerator clientGenerator;

    public static TransactionStore getInstance() {
        return new TransactionStore();
    }

    private TransactionStore() {
        clientGenerator = new ClientGenerator();
    }

    public void getTransactionHistory(String token, final TransactionInterface transactionInterface) {
        TransactionClient transactionClient = clientGenerator.createClient(TransactionClient.class);
        Call<List<Transaction>> call = transactionClient.getTransactionHistory(token);
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful()) {
                    transactionInterface.onSuccess(response.body());
                } else {
                    transactionInterface.onFailure("" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                transactionInterface.onFailure("" + t.getLocalizedMessage());
            }
        });
    }

    public void getDetailedTransaction(String token, Transaction transaction, final DetailTransactionCallback detailTransactionCallback) {
        TransactionClient transactionClient = clientGenerator.createClient(TransactionClient.class);
        Call<TransactionDetail> call = transactionClient.getDetailedTransaction(token, transaction.getReferenceNumber(), transaction.getId(), transaction.getSenderLastName(), transaction.getOnlineTransaction());
        call.enqueue(new Callback<TransactionDetail>() {
            @Override
            public void onResponse(Call<TransactionDetail> call, Response<TransactionDetail> response) {
                if (response.isSuccessful()) {
                    detailTransactionCallback.onSuccess(response.body());
                } else {
                    detailTransactionCallback.onFailure("Error");
                }
            }

            @Override
            public void onFailure(Call<TransactionDetail> call, Throwable t) {
                detailTransactionCallback.onFailure("Error");
            }
        });
    }

    public void getPastReceivers(String token, final ReceiverCallback receiverCallback) {
        TransactionClient transactionClient = clientGenerator.createClient(TransactionClient.class);
        Call<Recevicer> call = transactionClient.getBeneficary(token);
        call.enqueue(new Callback<Recevicer>() {
            @Override
            public void onResponse(Call<Recevicer> call, Response<Recevicer> response) {
                if (response.isSuccessful()) {
                    receiverCallback.onSuccess(response.body());
                } else {
                    receiverCallback.onFailure("something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Recevicer> call, Throwable t) {
                receiverCallback.onFailure("something went wrong");
            }
        });
    }


}
