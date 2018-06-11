package com.bitnudge.ime.demo.keyViews;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.libs.Util;
import com.bobblekeyboard.ime.BobbleEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MPinView implements KeyView, View.OnClickListener, View.OnFocusChangeListener, BobbleEditText.OnKeyListener {
    private final String TAG = getClass().getName();

    @BindView(R.id.edt_password)
    BobbleEditText edtPassword;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.img_next)
    ImageView imgNext;

    @BindView(R.id.layout_parent)
    LinearLayout layoutParent;

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Boolean inProgress;
    private View v;


    private MPinView(final CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_m_pin, null);
        unbinder = ButterKnife.bind(this, v);

        edtPassword.setOnFocusChangeListener(this);
        edtPassword.setOnClickListener(this);
        edtPassword.setOnKeyListener(this);
        edtPassword.requestFocus();

        inProgress = false;
    }

    public static MPinView getInstance(CustomViewManager customViewManager) {
        return new MPinView(customViewManager);
    }

    @OnClick(R.id.img_back)
    void onCLickBack() {
        if(inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        edtPassword.clearFocus();

        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showSelectionBar();
    }

    @OnClick(R.id.img_next)
    void onCLickNext() {
        goForward();
    }

    @Override
    public View getView() {
        return v;
    }

    @Override
    public void destroy() {
        mCustomIme = null;
        customViewManager = null;
        unbinder.unbind();
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
        if(hasFocus) {
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            goForward();
            return true;
        }

        return false;
    }

    private void goForward() {
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        if (edtPassword.getText().length() == 4) {
            edtPassword.clearFocus();
            try {
                mCustomIme.restoreInputTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Util.hideView(mCustomIme, layoutParent);
            customViewManager.showMenuView();
        } else {
            inProgress = false;
            Toast.makeText(mCustomIme, "MPin is a 4 digit number", Toast.LENGTH_SHORT).show();
        }
    }
}
