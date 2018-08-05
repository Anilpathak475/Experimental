package com.bitnudge.ime.moneygram.keyViews;

import android.view.LayoutInflater;
import android.view.View;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.bitnudge.ime.moneygram.core.CustomViewManager;
import com.bitnudge.ime.moneygram.interfaces.KeyView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecevierDetailView implements KeyView {
    private String TAG = this.getClass().getSimpleName();

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;

    private RecevierDetailView(CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_receiver_details, null);
        unbinder = ButterKnife.bind(this, v);
    }

    public static RecevierDetailView getInstance(CustomViewManager customViewManager) {
        return new RecevierDetailView(customViewManager);
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
