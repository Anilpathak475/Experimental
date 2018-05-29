package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.libs.Util;
import com.bobblekeyboard.ime.BobbleEditText;

public class PayView implements View.OnClickListener, View.OnFocusChangeListener {
    private String TAG = this.getClass().getSimpleName();

    private BobbleEditText searchText;
    private CustomIME mCustomIme;
    private View v;

    private PayView(CustomIME context) {
        this.mCustomIme = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.demo_view_layout, null);

        searchText = v.findViewById(R.id.demo_text);
        searchText.setOnFocusChangeListener(this);
        searchText.setOnClickListener(this);
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
    }

    public static PayView getInstance(CustomIME context) {
        return new PayView(context);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.demo_text:
                    searchText.requestFocus();
                    mCustomIme.setInputTarget(searchText);
                    break;
            }
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        try {
            switch (v.getId()) {
                case R.id.demo_text:
                    mCustomIme.restoreInputTarget();
                    mCustomIme.setInputTarget(searchText);
                    break;
            }
        } catch (Exception e) {
            Util.logException(TAG, "onFocusChange", e);
        }
    }
}
