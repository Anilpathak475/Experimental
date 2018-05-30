package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;

import butterknife.ButterKnife;

public class RecevierDetailView {

    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;
    private View v;


    private RecevierDetailView(CustomIME context) {
        this.mCustomIme = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.layout_receiver_details, null);
        ButterKnife.bind(this, v);
    }

    public static RecevierDetailView getInstance(CustomIME context) {
        return new RecevierDetailView(context);
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
    }

}
