package com.bitnudge.ime.demo.keyViews;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.libs.Util;
import com.bobblekeyboard.ime.BobbleEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MPinView implements View.OnClickListener, View.OnFocusChangeListener, BobbleEditText.OnKeyListener {
    private final String TAG = getClass().getName();

    @BindView(R.id.edt_password)
    BobbleEditText edtPassword;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.img_next)
    ImageView imgNext;

    @BindView(R.id.layout_parent)
    RelativeLayout layoutParent;

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Boolean inProgress;
    private View v;


    private MPinView(final CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_m_pin, null);
        ButterKnife.bind(this, v);
        edtPassword.setOnFocusChangeListener(this);
        edtPassword.setOnClickListener(this);
        edtPassword.setOnKeyListener(this);
        edtPassword.requestFocus();
        edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    onClickNext();
                }
                return false;
            }
        });
        inProgress = false;
    }

    public static MPinView getInstance(CustomViewManager customViewManager) {
        return new MPinView(customViewManager);
    }

    @OnClick(R.id.img_back)
    void onCLickBack() {
        if(inProgress) return;
        inProgress = true;

        edtPassword.clearFocus();
        mCustomIme.onFinishInput();
        try {
            mCustomIme.restoreInputTarget();
        } catch (Exception e) {
            e.printStackTrace();
        }
        layoutParent.startAnimation(Util.hideView());
        layoutParent.setVisibility(View.GONE);
        customViewManager.restoreToSelectionBar();
        customViewManager.addTopBarViewOnKeyboarBoardTop();
    }

    @OnClick(R.id.img_next)
    void onClickNext() {
        goForward();
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
        edtPassword = null;
        imgBack = null;
        imgNext = null;
        layoutParent = null;
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            goForward();
            return true;
        }
        return false;
    }

    private void goForward() {
        if(inProgress) return;
        inProgress = true;

        if (edtPassword.getText().length() == 4) {
            edtPassword.clearFocus();
            try {
                mCustomIme.restoreInputTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }
            layoutParent.startAnimation(Util.hideView());
            layoutParent.setVisibility(View.GONE);
            customViewManager.showSelectToPayView();
        } else {
            Toast.makeText(mCustomIme, "MPin is a 4 digit number", Toast.LENGTH_SHORT).show();
        }
    }
}
