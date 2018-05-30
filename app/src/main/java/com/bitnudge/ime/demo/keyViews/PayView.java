package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.CountrySpinnerAdapter;
import com.bitnudge.ime.demo.adapter.SpinnerAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.modle.Card;
import com.bitnudge.ime.demo.modle.Country;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayView implements View.OnClickListener, View.OnFocusChangeListener {
    @BindView(R.id.spn_payment_details)
    Spinner spnCardDetails;

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.img_add_card)
    ImageView imgAddCard;

    @BindView(R.id.img_country)
    Spinner imgCountry;

    private CustomIME mCustomIme;
    private View v;


    private PayView(CustomIME context) {
        this.mCustomIme = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.layout_pay_view, null);
        ButterKnife.bind(this, v);
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("1234 5678 8907 4433"));
        cards.add(new Card("1234 5678 8907 4433"));
        cards.add(new Card("1234 5678 8907 4433"));
        cards.add(new Card("1234 5678 8907 4433"));
        cards.add(new Card("1234 5678 8907 4433"));
        spnCardDetails.setAdapter(new SpinnerAdapter(mCustomIme, R.layout.layout_spinner_item, cards));

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("India", R.drawable.ic_united_states));
        countries.add(new Country("Usa", R.drawable.ic_united_arab_emirates));
        countries.add(new Country("England", R.drawable.exchange));
        countries.add(new Country("Russia", R.drawable.ic_add));
        imgCountry.setAdapter(new CountrySpinnerAdapter(mCustomIme, R.layout.layout_country_spinner_item, countries));
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
               /* case R.id.demo_text:
                    searchText.requestFocus();
                    mCustomIme.setInputTarget(searchText);
                    break;*/
            }
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        try {
            switch (v.getId()) {
               /* case R.id.demo_text:
                    mCustomIme.restoreInputTarget();
                    mCustomIme.setInputTarget(searchText);
                    break;*/
            }
        } catch (Exception e) {
            Util.logException(TAG, "onFocusChange", e);
        }
    }
}
