package com.bitnudge.ime.moneygram.client;

import com.bitnudge.ime.moneygram.model.UserCredential;
import com.bitnudge.ime.moneygram.model.UserDetails;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface LoginClient {
    @POST("/api/v1/users/login/")
    Call<UserDetails> login(@Body UserCredential userCredential);

}
