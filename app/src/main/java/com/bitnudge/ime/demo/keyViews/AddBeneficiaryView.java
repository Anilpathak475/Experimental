package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBeneficiaryView {

    @BindView(R.id.spn_country_code)
    Spinner spnCountryCode;

    @BindView(R.id.btn_next)
    Button btnNext;
    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;


    private AddBeneficiaryView(CustomViewManager customViewManager) {
        this.customViewManager = customViewManager;
        this.mCustomIme = customViewManager.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_add_benefeciary, null);

        ButterKnife.bind(this, v);
        List<String> codes = new ArrayList<>();
        codes.add("Select");
        codes.add("+91");
        codes.add("+92");
        codes.add("+93");
        codes.add("+94");
        codes.add("+95");
        spnCountryCode.setAdapter(new ArrayAdapter<String>(mCustomIme, android.R.layout.simple_dropdown_item_1line, codes));

    }

    public static AddBeneficiaryView getInstance(CustomViewManager customViewManager) {
        return new AddBeneficiaryView(customViewManager);
    }

    @OnClick(R.id.btn_next)
    void onClickNext() {
        customViewManager.showSelectToPayView();
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
    }
}
