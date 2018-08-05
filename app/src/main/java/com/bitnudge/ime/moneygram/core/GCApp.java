package com.bitnudge.ime.moneygram.core;

import android.app.Application;

import com.bitnudge.ime.moneygram.libs.Util;

public class GCApp extends Application {
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Util.loginGate(this);
        Util.logDebug(TAG, "Application launched");
    }
}
