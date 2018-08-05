package com.bitnudge.ime.moneygram.core;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import com.android.inputmethod.latin.permissions.PermissionsActivity;
import com.bitnudge.ime.moneygram.libs.DialogFlow;
import com.bitnudge.ime.moneygram.libs.InvalidConfigurationException;
import com.bitnudge.ime.moneygram.libs.Util;
import com.bobblekeyboard.ime.BobbleIME;
import com.bobblekeyboard.ime.BobbleIMETheme;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Adhityan on 17/03/18.
 */

public class CustomIME extends BobbleIME {
    public CustomViewManager mCustomViewManager;
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Util.loginGate(this);
        Fresco.initialize(this);
        mCustomViewManager = new CustomViewManager(this);
    }

    private boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                PermissionsActivity.run(this, 1008, android.Manifest.permission.LOCATION_HARDWARE, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    protected void onStartIME() {
        mCustomViewManager.showSelectionBar();
    }


    @Override
    protected void onFinishIME() {

    }

    @Override
    protected void onSetDefaultTheme() {
        try {
            setIMETheme(BobbleIMETheme.LIGHT);
        } catch (Exception e) {
            Util.logException(TAG, "onSetDefaultTheme", e);
        }
    }
}
