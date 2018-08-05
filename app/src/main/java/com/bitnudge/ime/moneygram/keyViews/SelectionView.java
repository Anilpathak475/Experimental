package com.bitnudge.ime.moneygram.keyViews;

import android.view.LayoutInflater;
import android.view.View;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.bitnudge.ime.moneygram.core.CustomViewManager;
import com.bitnudge.ime.moneygram.interfaces.KeyView;
import com.bitnudge.ime.moneygram.libs.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectionView implements KeyView {
    private final String TAG = getClass().getName();

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Boolean inProgress;
    private View v;


    private SelectionView(final CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.top_bar_view_layout, null);
        unbinder = ButterKnife.bind(this, v);

        inProgress = false;
    }

    public static SelectionView getInstance(CustomViewManager customViewManager) {
        return new SelectionView(customViewManager);
    }

    @OnClick({ R.id.img_btn_pay, R.id.img_btn_chat })
    public void onClick(View v) {
        if (!v.isEnabled()) return;
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);

        switch (v.getId()) {
            case R.id.img_btn_pay:
                customViewManager.showMPinView();
                break;
            case R.id.img_btn_chat:
                customViewManager.showBotView();
                break;
        }
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
}
