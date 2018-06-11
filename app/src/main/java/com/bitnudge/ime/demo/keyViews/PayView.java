package com.bitnudge.ime.demo.keyViews;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.CountrySpinnerAdapter;
import com.bitnudge.ime.demo.adapter.SpinnerAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.model.Card;
import com.bitnudge.ime.demo.model.Country;
import com.bitnudge.ime.demo.model.PayToContainer;
import com.bobblekeyboard.ime.BobbleEditText;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PayView implements KeyView, View.OnClickListener, View.OnFocusChangeListener, BobbleEditText.OnKeyListener {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.spn_payment_details)
    Spinner spnCardDetails;

    @BindView(R.id.spn_converting_country)
    Spinner spnConvertingCountry;

    @BindView(R.id.img_add_card)
    ImageView imgAddCard;

    @BindView(R.id.img_country)
    Spinner imgCountry;

    @BindView(R.id.txt_pay_name)
    TextView txtPayName;

    @BindView(R.id.img_icon)
    ImageView imgIcon;

    @BindView(R.id.edt_currency)
    EditText edtCurrency;

    @BindView(R.id.edt_converting_rate)
    EditText edtConvertingRate;

    @BindView(R.id.layout_parent)
    FrameLayout layoutParent;

    @BindView(R.id.confirm_payment_overlay)
    RelativeLayout confirmOverlay;


    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;

    private List<Card> cards;
    private boolean inProgress;
    private PayToContainer payTo;

    private PayView(final CustomViewManager customViewManager, PayToContainer payTo) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;
        this.payTo = payTo;

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_pay_view, null);
        unbinder = ButterKnife.bind(this, v);

        setDefaultAdapter();
        edtConvertingRate.setOnClickListener(this);
        edtConvertingRate.setOnFocusChangeListener(this);

        edtCurrency.setOnClickListener(this);
        edtCurrency.setOnFocusChangeListener(this);
        edtCurrency.setOnKeyListener(this);
        edtConvertingRate.setOnKeyListener(this);

        txtPayName.setText(payTo.getName());
        imgIcon.setBackground(mCustomIme.getResources().getDrawable(payTo.getCard().getDrawableId()));

        imgCountry.setEnabled(false);
        spnCardDetails.setEnabled(false);
        spnConvertingCountry.setEnabled(false);
    }

    public static PayView getInstance(CustomViewManager customViewManager, PayToContainer payTo) {
        return new PayView(customViewManager, payTo);
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
        customViewManager = null;
        unbinder.unbind();
    }

    private void setDefaultAdapter() {
        cards = new ArrayList<>();
        cards.add(new Card("1234 5678 8907 4454", R.drawable.icici));
        cards.add(new Card("1234 5678 8907 4476", R.drawable.hdfc));
        cards.add(new Card("1234 5678 8907 4487", R.drawable.rbs));
        cards.add(new Card("1234 5678 8907 4412", R.drawable.hsbc));
        spnCardDetails.setAdapter(new SpinnerAdapter(mCustomIme, R.layout.layout_spinner_item, cards));

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("USA", R.drawable.ic_united_states));
        countries.add(new Country("India", R.drawable.ic_india_flag));
        countries.add(new Country("UAE", R.drawable.ic_united_arab_emirates));
        countries.add(new Country("England", R.drawable.ic_england_flag));

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
        spnConvertingCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgCountry.setSelection(0);
        spnConvertingCountry.setSelection(2);
    }

    @OnClick(R.id.img_back)
    void onClickBack() {
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        mCustomIme.onFinishInput();
        try {
            mCustomIme.restoreInputTarget();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showSelectBenefciaryView();
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
        if(keyCode == KeyEvent.KEYCODE_DEL) {
            if(v.getId() == R.id.edt_currency) {
                String text = edtCurrency.getText().toString();

                if (text.length() > 0) {
                    text = text.replaceAll(",", "");
                    float value = Float.parseFloat(text);
                    value = value * 3.67f;
                    edtConvertingRate.setText(String.valueOf(value));
                } else edtConvertingRate.setText("0");
            }
            else {
                String text = edtConvertingRate.getText().toString();

                if (text.length() > 0) {
                    text = text.replaceAll(",", "");
                    float value = Float.parseFloat(text);
                    value = value * 3.67f;
                    edtCurrency.setText(String.valueOf(value));
                } else edtCurrency.setText("0");
            }
        }
        else if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            String value = edtConvertingRate.getText().toString();

            if(value.length() > 0 && !value.equals("0")) {
                if(inProgress) return true;
                inProgress = true;

                edtCurrency.clearFocus();
                edtConvertingRate.clearFocus();
                confirmOverlay.setVisibility(View.VISIBLE);
                confirmOverlay.setClickable(true);
                confirmOverlay.setFocusable(true);

                try {
                    mCustomIme.sendTextToConnectedInput(MessageFormat.format("You have been paid {0} AED. Click https://goo.gl/sRRXmU for the receipt.", value));

                    mCustomIme.onFinishInput();
                    mCustomIme.restoreInputTarget();
                } catch (Exception e) { Util.logException(TAG, "onKey", e); }
            }
            else Toast.makeText(mCustomIme, "To transfer, please fill in the amount.", Toast.LENGTH_SHORT).show();

            return true;
        }
        else if(v.getId() == R.id.edt_currency) {
            String text = edtCurrency.getText().toString();

            if(text.length() > 0) {
                text = text.replaceAll(",", "");
                float value = Float.parseFloat(text);
                value = value * 3.67f;
                edtConvertingRate.setText(String.valueOf(value));
            }
            else edtConvertingRate.setText("0");
        }
        else if(v.getId() == R.id.edt_converting_rate) {
            String text = edtConvertingRate.getText().toString();

            if(text.length() > 0) {
                text = text.replaceAll(",", "");
                float value = Float.parseFloat(text);
                value = value * 0.27f;
                edtCurrency.setText(String.valueOf(value));
            }
            else edtCurrency.setText("0");
        }

        return false;
    }

    private String extractLastFourDigits(String cardNo) {
        cardNo = cardNo.substring(cardNo.length() - 5, cardNo.length());
        return cardNo;
    }
}
