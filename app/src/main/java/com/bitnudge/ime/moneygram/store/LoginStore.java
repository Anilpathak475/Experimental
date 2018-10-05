package com.bitnudge.ime.moneygram.store;

import com.bitnudge.ime.moneygram.client.LoginClient;
import com.bitnudge.ime.moneygram.interfaces.LoginInterface;
import com.bitnudge.ime.moneygram.model.UserCredential;
import com.bitnudge.ime.moneygram.model.UserDetails;
import com.bitnudge.ime.moneygram.network.ClientGenerator;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginStore {
    private ClientGenerator clientGenerator;

    public static LoginStore getInstance() {
        return new LoginStore();
    }

    private LoginStore() {
        clientGenerator = new ClientGenerator();
    }

    public void login(UserCredential userCredential, final LoginInterface loginInterface) {
        final LoginClient loginClient = clientGenerator.createClient(LoginClient.class);
        Call<UserDetails> call = loginClient.login(userCredential);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (response.isSuccessful()) {
                    loginInterface.onSuccess(response.body());
                } else {
                    loginInterface.onFailure(String.format(Locale.ENGLISH, "%d", response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                loginInterface.onFailure(String.format(Locale.ENGLISH,"%d", t.getLocalizedMessage()));

            }
        });
    }
}
