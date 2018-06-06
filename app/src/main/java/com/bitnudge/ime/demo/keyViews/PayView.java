package com.bitnudge.ime.demo.keyViews;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayView implements View.OnClickListener, View.OnFocusChangeListener, BobbleEditText.OnKeyListener {
    @BindView(R.id.spn_payment_details)
    Spinner spnCardDetails;

    @BindView(R.id.spn_converting_country)
    Spinner spnConvertingCountry;

    @BindView(R.id.img_add_card)
    ImageView imgAddCard;

    @BindView(R.id.img_back)
    ImageView imgBack;

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

    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private PayTo payTo;
    private View v;
    private List<Card> cards;
    private boolean inProgress;

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

        imgBack.setOnClickListener(this);
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
        cards.add(new Card("1234 5678 8907 4454", R.drawable.icici));
        cards.add(new Card("1234 5678 8907 4476", R.drawable.hdfc));
        cards.add(new Card("1234 5678 8907 4487", R.drawable.rbs));
        cards.add(new Card("1234 5678 8907 4412", R.drawable.hsbc));
        spnCardDetails.setAdapter(new SpinnerAdapter(mCustomIme, R.layout.layout_spinner_item, cards));

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("India", R.drawable.ic_united_states));
        countries.add(new Country("Usa", R.drawable.ic_united_arab_emirates));
        countries.add(new Country("England", R.drawable.exchange));
        countries.add(new Country("Russia", R.drawable.ic_add));

        imgCountry.setAdapter(new CountrySpinnerAdapter(mCustomIme, R.layout.layout_country_spinner_item, countries));
        imgCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnConvertingCountry.setAdapter(new CountrySpinnerAdapter(mCustomIme, R.layout.layout_country_spinner_item, countries));
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
                case R.id.img_back:
                    customViewManager.showSelectToPayView();
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
        if(keyCode == KeyEvent.KEYCODE_DEL) {
            if(v.getId() == R.id.edt_currency) {
                String text = edtCurrency.getText().toString();

                if (text.length() > 4) {
                    text = text.substring(4).trim().replaceAll(",", "");
                    float value = Float.parseFloat(text);
                    value = value * 3.67f;
                    edtConvertingRate.setText(MessageFormat.format("AED {0}", value));
                } else if (text.length() == 4) edtConvertingRate.setText("AED 0");
                else return true;
            }
            else {
                String text = edtConvertingRate.getText().toString();

                if (text.length() > 4) {
                    text = text.substring(4).trim().replaceAll(",", "");
                    float value = Float.parseFloat(text);
                    value = value * 3.67f;
                    edtCurrency.setText(MessageFormat.format("USD {0}", value));
                } else if (text.length() == 4) edtCurrency.setText("USD 0");
                else return true;
            }
        }
        else if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            if(edtConvertingRate.getText().length() > 4) {
                if(inProgress) return true;
                inProgress = true;

                layoutParent.startAnimation(Util.hideView());
                layoutParent.setVisibility(View.GONE);
                Transaction transaction = new Transaction();
                transaction.setName(payTo.getName());
                transaction.setCard(cards.get(spnCardDetails.getSelectedItemPosition()));
                transaction.setAmount(edtConvertingRate.getText().toString());
                transaction.setNotes("-");
                transaction.setStatus("Successful");
                transaction.setDate(new Date());
                transaction.setCurrency(edtCurrency.getText().toString());
                customViewManager.showPaymentDetailsView(transaction);
            }
            else Toast.makeText(mCustomIme, "To transfer, please fill in the amount.", Toast.LENGTH_SHORT).show();

            return true;
        }
        else if(v.getId() == R.id.edt_currency) {
            String text = edtCurrency.getText().toString();

            if(text.length() > 4) {
                text = text.substring(4).trim().replaceAll(",", "");
                float value = Float.parseFloat(text);
                value = value * 3.67f;
                edtConvertingRate.setText(MessageFormat.format("AED {0}", value));
            }
            else edtConvertingRate.setText("AED 0");
        }
        else if(v.getId() == R.id.edt_converting_rate) {
            String text = edtConvertingRate.getText().toString();

            if(text.length() > 4) {
                text = text.substring(4).trim().replaceAll(",", "");
                float value = Float.parseFloat(text);
                value = value * 0.27f;
                edtCurrency.setText(MessageFormat.format("USD {0}", value));
            }
            else edtCurrency.setText("USD 0");
        }

        return false;
    }

    private String extractLastFourDigits(String cardNo) {
        cardNo = cardNo.substring(cardNo.length() - 5, cardNo.length());
        return cardNo;
    }
}
