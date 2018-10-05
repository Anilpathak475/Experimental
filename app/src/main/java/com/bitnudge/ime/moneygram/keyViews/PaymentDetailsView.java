package com.bitnudge.ime.moneygram.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.bitnudge.ime.moneygram.core.CustomViewManager;
import com.bitnudge.ime.moneygram.interfaces.DetailTransactionCallback;
import com.bitnudge.ime.moneygram.interfaces.KeyView;
import com.bitnudge.ime.moneygram.libs.CalenderUtil;
import com.bitnudge.ime.moneygram.libs.Util;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.model.TransactionDetail;
import com.bitnudge.ime.moneygram.store.TransactionStore;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PaymentDetailsView  implements KeyView {
    private final String TAG = getClass().getName();

    @BindView(R.id.img_close)
    ImageView imgClose;

    @BindView(R.id.txt_date)
    TextView txtDate;

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

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;

    private Transaction transaction;

    private PaymentDetailsView(final CustomViewManager customViewManager, Transaction transaction) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;


        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_payment_details, null);
        unbinder = ButterKnife.bind(this, v);

        getTransactionDetails(transaction);
       /* txtCardNo.setText(transaction.get().getCardNo());*/


    }

    private void setValues(Transaction transaction) {
        this.transaction = transaction;
        txtPayName.setText(String.format("%s %s", transaction.getSenderFirstName(), transaction.getSenderLastName()));
        txtPaymentAmount.setText(String.format(Locale.ENGLISH,"%d", transaction.getSendAmount()));
        txtStatus.setText(transaction.getStatus());
        txtReason.setText(String.format(Locale.ENGLISH,"%d", transaction.getAuthorizationId()));
        txtDate.setText(CalenderUtil.getFormatedDateFromDate(transaction.getDateInitiated()));
    }

    public static PaymentDetailsView getInstance(CustomViewManager customViewManager, Transaction transaction) {
        return new PaymentDetailsView(customViewManager, transaction);
    }

    @OnClick(R.id.img_close)
    void onClose() {
        Util.makeTapSound(mCustomIme);
        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showTransactionView();
    }

    @OnClick(R.id.btn_live_chat)
    void onChat() {
        Util.makeTapSound(mCustomIme);
        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showBotView();
    }

    private void getTransactionDetails(final Transaction transaction) {
        TransactionStore.getInstance().getDetailedTransaction(customViewManager.getUserDetails().getAccesTokenInfo().getAccess_token(), transaction, new DetailTransactionCallback() {
            @Override
            public void onSuccess(TransactionDetail transactionDetail) {
                setValues(transactionDetail.getTransaction());
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @OnClick(R.id.btn__update_info)
    void onUpdate() {
        Util.makeTapSound(mCustomIme);
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

