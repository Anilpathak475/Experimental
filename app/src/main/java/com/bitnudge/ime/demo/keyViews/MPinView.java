package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.libs.Util;
import com.bobblekeyboard.ime.BobbleEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MPinView implements View.OnClickListener, View.OnFocusChangeListener {

    private final String TAG = getClass().getName();
    @BindView(R.id.edt_password)
    BobbleEditText edtPassword;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.img_next)
    ImageView imgNext;


    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;

    private MPinView(final CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_m_pin, null);
        ButterKnife.bind(this, v);
        edtPassword.setOnFocusChangeListener(this);
        edtPassword.setOnClickListener(this);
        edtPassword.requestFocus();
    }

    public static MPinView getInstance(CustomViewManager customViewManager) {
        return new MPinView(customViewManager);
    }

    @OnClick(R.id.img_back)
    void onCLickBack() {
        customViewManager.restoreToSelectionBar();
        customViewManager.addTopBarViewOnKeyboarBoardTop();
    }

    @OnClick(R.id.img_next)
    void onCLickNext() {
        if (edtPassword.getText().length() == 6) {
            edtPassword.clearFocus();
            customViewManager.restoreToSelectionBar();
            customViewManager.showSelectToPayView();
            mCustomIme.onFinishInput();
        } else {
            Toast.makeText(mCustomIme, "Passowrd must contains 6 digits", Toast.LENGTH_SHORT).show();
        }
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.edt_password:
                    edtPassword.requestFocus();
                    mCustomIme.setInputTarget(edtPassword);
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
                case R.id.edt_password:
                    mCustomIme.restoreInputTarget();
                    mCustomIme.setInputTarget(edtPassword);
                    break;
            }
        } catch (Exception e) {
            Util.logException(TAG, "onFocusChange", e);
        }
    }
}
