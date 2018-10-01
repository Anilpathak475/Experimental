package com.bitnudge.ime.moneygram.interfaces;

import com.bitnudge.ime.moneygram.model.UserDetails;

public interface LoginInterface {
    void onSuccess(UserDetails userDetails);

    void onFailure(String error);
}
