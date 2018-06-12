package com.bitnudge.ime.demo.core;

import android.app.Application;

import com.bitnudge.ime.demo.libs.Util;

public class GCApp extends Application {
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Util.loginGate(this);
        Util.logDebug(TAG, "Application launched");
    }
}
