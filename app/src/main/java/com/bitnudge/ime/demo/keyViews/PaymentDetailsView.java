package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.modle.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentDetailsView {
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_day)
    TextView txtDay;
    @BindView(R.id.txt_pay_name)
    TextView txtPayName;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_card_no)
    TextView txtCardNo;
    @BindView(R.id.txt_payment_amount)
    TextView txtPaymentAmount;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.txt_reason)
    TextView txtReason;
    @BindView(R.id.btn_live_chat)
    Button btnLiveChat;
    @BindView(R.id.btn__update_info)
    Button btnUpdateInfo;
    @BindView(R.id.layout_parent)
    ScrollView layoutParent;

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;
    private Transaction transaction;

    private PaymentDetailsView(final CustomViewManager customViewManager, Transaction transaction) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;
        this.transaction = transaction;

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_payment_details, null);
        ButterKnife.bind(this, v);

        txtPayName.setText(transaction.getName());
        txtCardNo.setText(transaction.getCard().getCardNo());
        txtPaymentAmount.setText(transaction.getAmount());
        txtStatus.setText(transaction.getStatus());
        txtReason.setText(transaction.getNotes());
        txtDate.setText(getDateFromDate(transaction.getDate()));
        txtDay.setText(getDay(transaction.getDate()));
        //todo: set flag
    }

    public static PaymentDetailsView getInstance(CustomViewManager customViewManager, Transaction transaction) {
        return new PaymentDetailsView(customViewManager, transaction);
    }

    @OnClick(R.id.img_close)
    void onClose() {
        customViewManager.restoreToSelectionBar();
    }

    @OnClick(R.id.btn_live_chat)
    void onChat() {
        layoutParent.startAnimation(Util.hideView());
        layoutParent.setVisibility(View.GONE);
        customViewManager.showBotView();
    }

    @OnClick(R.id.btn__update_info)
    void onUpdate() {
        layoutParent.startAnimation(Util.hideView());
        layoutParent.setVisibility(View.GONE);
        customViewManager.showBotView();
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
        txtDay = null;
        txtDate = null;
        txtReason = null;
        txtStatus = null;
        txtPaymentAmount = null;
        txtCardNo = null;
        txtPayName = null;
        btnLiveChat = null;
        btnUpdateInfo = null;
    }

    public String getDateFromDate(Date date) {
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formatDate, Locale.ENGLISH);
        return dateFormatter.format(date);
    }

    public String getDay(Date date) {
        String dateFormat = "EEEE";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return dateFormatter.format(date);
    }
}

