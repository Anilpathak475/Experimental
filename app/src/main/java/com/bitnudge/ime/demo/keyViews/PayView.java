package com.bitnudge.ime.demo.keyViews;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.CountrySpinnerAdapter;
import com.bitnudge.ime.demo.adapter.SpinnerAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.modle.Card;
import com.bitnudge.ime.demo.modle.Country;
import com.bitnudge.ime.demo.modle.PayTo;
import com.bitnudge.ime.demo.modle.Transaction;
import com.bobblekeyboard.ime.BobbleEditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayView implements View.OnClickListener, View.OnFocusChangeListener, BobbleEditText.OnKeyListener {
    @BindView(R.id.spn_payment_details)
    Spinner spnCardDetails;

    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.img_add_card)
    ImageView imgAddCard;

    @BindView(R.id.img_country)
    Spinner imgCountry;

    @BindView(R.id.txt_pay_name)
    TextView txtPayName;

    @BindView(R.id.img_icon)
    ImageView imgIcon;

    @BindView(R.id.txt_card_no)
    TextView txtCardNo;

    @BindView(R.id.edt_currency)
    EditText edtCurrency;

    @BindView(R.id.edt_converting_rate)
    EditText edtConvertingRate;

    @BindView(R.id.layout_parent)
    ScrollView layoutParent;

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private PayTo payTo;
    private View v;
    private List<Card> cards;

    private PayView(final CustomViewManager customViewManager, PayTo payTo) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;
        this.payTo = payTo;
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_pay_view, null);
        ButterKnife.bind(this, v);
        setDefaultAdapter();

        edtConvertingRate.setOnClickListener(this);
        edtConvertingRate.setOnFocusChangeListener(this);

        edtCurrency.setOnClickListener(this);
        edtCurrency.setOnFocusChangeListener(this);
        edtCurrency.setOnKeyListener(this);
        edtConvertingRate.setOnKeyListener(this);
        txtPayName.setText(payTo.getName());
        txtCardNo.setText(extractLastFourDigits(payTo.getCard().getCardNo()));
        imgIcon.setBackground(mCustomIme.getResources().getDrawable(payTo.getCard().getId()));
    }

    public static PayView getInstance(CustomViewManager customViewManager, PayTo payTo) {
        return new PayView(customViewManager, payTo);
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
        edtConvertingRate = null;
        edtCurrency = null;
        txtCardNo = null;
        spnCardDetails = null;
        imgIcon = null;
        imgCountry = null;
        imgAddCard = null;
    }

    private void setDefaultAdapter() {
        cards = new ArrayList<>();
        cards.add(new Card("1234 5678 8907 4433", R.drawable.icici));
        cards.add(new Card("1234 5678 8907 4433", R.drawable.hdfc));
        cards.add(new Card("1234 5678 8907 4433", R.drawable.rbs));
        cards.add(new Card("1234 5678 8907 4433", R.drawable.hsbc));
        spnCardDetails.setAdapter(new SpinnerAdapter(mCustomIme, R.layout.layout_spinner_item, cards));

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("India", R.drawable.ic_united_states));
        countries.add(new Country("Usa", R.drawable.ic_united_arab_emirates));
        countries.add(new Country("England", R.drawable.exchange));
        countries.add(new Country("Russia", R.drawable.ic_add));

        imgCountry.setAdapter(new CountrySpinnerAdapter(mCustomIme, R.layout.layout_country_spinner_item, countries));
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.edt_currency:
                    edtCurrency.requestFocus();
                    mCustomIme.setInputTarget(edtCurrency);
                    break;
                case R.id.edt_converting_rate:
                    edtConvertingRate.requestFocus();
                    mCustomIme.setInputTarget(edtConvertingRate);
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
                case R.id.edt_currency:
                    mCustomIme.restoreInputTarget();
                    mCustomIme.setInputTarget(edtCurrency);
                    break;
                case R.id.edt_converting_rate:
                    mCustomIme.restoreInputTarget();
                    mCustomIme.setInputTarget(edtConvertingRate);
                    break;
            }
        } catch (Exception e) {
            Util.logException(TAG, "onFocusChange", e);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            layoutParent.startAnimation(Util.hideView());
            layoutParent.setVisibility(View.GONE);
            Transaction transaction = new Transaction();
            transaction.setCard(cards.get(spnCardDetails.getSelectedItemPosition()));
            transaction.setAmount(edtConvertingRate.getText().toString());
            transaction.setNotes("Cards Added ");
            transaction.setStatus("Success");
            transaction.setDate(new Date());
            transaction.setCurrency(edtCurrency.getText().toString());
            customViewManager.showPaymentDetailsView(transaction);
            return true;
        }
        return false;
    }

    private String extractLastFourDigits(String cardNo) {
        cardNo = cardNo.substring(cardNo.length() - 5, cardNo.length());
        return cardNo;
    }
}
