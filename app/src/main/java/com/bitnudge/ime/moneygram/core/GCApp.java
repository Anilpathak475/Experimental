package com.bitnudge.ime.moneygram.core;

import android.app.Application;

import com.bitnudge.ime.moneygram.libs.Util;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class GCApp extends Application {
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Util.loginGate(this);
        Util.logDebug(TAG, "Application launched");
    }
}
