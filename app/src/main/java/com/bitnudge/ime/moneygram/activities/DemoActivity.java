package com.bitnudge.ime.moneygram.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.libs.Util;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoActivity extends AppCompatActivity {
    @BindView(R.id.keyboard_display)
    TextView keyboardDisplay;

    @BindView(R.id.device_display)
    TextView deviceDisplay;

    @BindView(R.id.lock_display)
    TextView lockDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String appName = getString(R.string.app_name);
        keyboardDisplay.setText(appName);

        String deviceId = Util.getDeviceId(this);
        deviceDisplay.setText(MessageFormat.format("Device id: {0}", deviceId));

        String lockId = Util.getLockIds(this);
        lockDisplay.setText(MessageFormat.format("Allowed: {0}", lockId));
    }
}
