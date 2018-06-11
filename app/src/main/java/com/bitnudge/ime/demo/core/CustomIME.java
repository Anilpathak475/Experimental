package com.bitnudge.ime.demo.core;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import com.android.inputmethod.latin.permissions.PermissionsActivity;
import com.bitnudge.ime.demo.libs.DialogFlow;
import com.bitnudge.ime.demo.libs.Util;
import com.bobblekeyboard.ime.BobbleIME;
import com.bobblekeyboard.ime.BobbleIMETheme;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Adhityan on 17/03/18.
 */

public class CustomIME extends BobbleIME implements DialogFlow.AIInterface {
    private static final String LICENCE_KEY = "W3sicGFja2FnZSI6IkxBenZpdFwvUUNmZUY5Smdna1dVXC9hUkd3RERHVG9waXN0Z1RHZWVOR0RPdz0iLCJpZCI6Miwia2V5IjoiakpxcGRyelN3bHBhd1UraWViYTYrOERvTzYwbE53UGNoU1k3b25TaWp6NWZHWmpEVmFOZ0xqV0UwSnZiNVhKVTI3QjNwQkUyUXczampRZ0dBZnViN1BqYUVtQytDTmlGOTQyQTBEMGlka2R0QzU2cHZHb3N0T2xqTVBCc1R6YXBzSVp4RWxcL2kxVlRzbXVSRmMyQkRCcmp5VW9NRlYraHZ6d3RxZmI4a1U0c002SUJwQ1VKNGJvbUcwTzBVb1VidDEwdnFkeTFSdWxcL1dmY0hhRW9zZmNHSENMVVpmTmZ2TzVQNytReXN2RkJpN1UxNzQrRjRIZG9RRXVDdUxRdmVhYWo4TFRQVUVZV0NZZkFcL3NSU0hUcCtTdG5ldDRSV3MxMHY4eVBuKzBzNVVDbUNLXC9RQmFMWWlObElGT3EzbFZpUzRQaVA2MzdvZEtpUDl4ZnlwTGxrZz09In1d";
    public CustomViewManager mCustomViewManager;
    private String TAG = this.getClass().getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        setLicenceKey(LICENCE_KEY);
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

    @Override
    public void aiResponse(String response) {

    }

    @Override
    public void aiError(String message) {

    }
}
