package com.bitnudge.ime.moneygram.store;

import com.bitnudge.ime.moneygram.client.TransactionClient;
import com.bitnudge.ime.moneygram.interfaces.TransactionInterface;
import com.bitnudge.ime.moneygram.model.Transaction;
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


}
