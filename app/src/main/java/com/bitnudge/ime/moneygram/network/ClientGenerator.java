package com.bitnudge.ime.moneygram.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anilpathak on 05/09/17.
 * Creating instance of retrofit to make url calls
 */

public class ClientGenerator {

    private Retrofit retrofit;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private void createRetrofit() {
        retrofit = null;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
                                      @Override
                                      public Response intercept(Interceptor.Chain chain) throws IOException {
                                          Request original = chain.request();

                                          Request request = original.newBuilder()
                                                  .header("clientkey", "Basic INNOVATION_9689668b-ebda-45ab-9e78-873443631afa")
                                                  .header("Content-Type", "Content-Type")
                                                  .header("Accpect", "Content-Type")
                                                  .method(original.method(), original.body())
                                                  .build();

                                          return chain.proceed(request);
                                      }
                                  });
        OkHttpClient client = httpClient.build();

        retrofit = new Retrofit.Builder()
/*
                .baseUrl("https://a3d4f6ad-fa16-42d9-903f-5db066a762c6.mock.pstmn.io/d5consumerapi.qa.moneygram.com/services/mgo/")
*/
                .baseUrl("https://92564b04-6693-4b58-89d3-e6b61cdfcab8.mock.pstmn.io/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <S> S createClient(Class<S> clientClass) {
        createRetrofit();
        return retrofit.create(clientClass);
    }
}
