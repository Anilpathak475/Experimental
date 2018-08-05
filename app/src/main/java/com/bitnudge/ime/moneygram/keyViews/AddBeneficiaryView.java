package com.bitnudge.ime.moneygram.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.bitnudge.ime.moneygram.core.CustomViewManager;
import com.bitnudge.ime.moneygram.interfaces.KeyView;
import com.bitnudge.ime.moneygram.libs.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddBeneficiaryView implements KeyView {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.spn_country_code)
    Spinner spnCountryCode;

    @BindView(R.id.edt_first_name)
    EditText edtFirstname;

    @BindView(R.id.edt_last_name)
    EditText edtLastName;

    @BindView(R.id.edt_phone)
    EditText edtPhone;

    @BindView(R.id.edt_receiving_country)
    EditText edtReceivingCountry;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.btn_next)
    Button btnNext;

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Unbinder unbinder;
    private View v;


    private AddBeneficiaryView(CustomViewManager customViewManager) {
        this.customViewManager = customViewManager;
        this.mCustomIme = customViewManager.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_add_benefeciary, null);
        unbinder = ButterKnife.bind(this, v);

        List<String> codes = new ArrayList<>();
        codes.add("+91");
        codes.add("+92");
        codes.add("+93");
        codes.add("+94");
        codes.add("+95");
        spnCountryCode.setAdapter(new ArrayAdapter<String>(mCustomIme, android.R.layout.simple_dropdown_item_1line, codes));
        //spnCountryCode.setEnabled(false);
    }

    public static AddBeneficiaryView getInstance(CustomViewManager customViewManager) {
        return new AddBeneficiaryView(customViewManager);
    }

    @OnClick({ R.id.btn_next, R.id.img_back })
    void onClickNext() {
        Util.makeTapSound(mCustomIme);
        customViewManager.showSelectBenefciaryView();
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
