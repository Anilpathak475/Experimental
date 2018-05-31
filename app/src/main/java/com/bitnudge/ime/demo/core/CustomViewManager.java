package com.bitnudge.ime.demo.core;

import android.content.Context;
import android.media.AudioManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.keyViews.AddBeneficiaryView;
import com.bitnudge.ime.demo.keyViews.BotKeyView;
import com.bitnudge.ime.demo.keyViews.MPinView;
import com.bitnudge.ime.demo.keyViews.PayView;
import com.bitnudge.ime.demo.keyViews.PaymentDetailsView;
import com.bitnudge.ime.demo.keyViews.RecevierDetailView;
import com.bitnudge.ime.demo.keyViews.SelectToPayView;
import com.bitnudge.ime.demo.keyViews.TransactionView;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.modle.PayTo;
import com.bitnudge.ime.demo.modle.Transaction;

/**
 * Created by Adhityan on 17/03/18.
 */

public class CustomViewManager implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;

    private PayView payView;
    private SelectToPayView selectToPayView;
    private TransactionView transactionView;
    private AddBeneficiaryView addBeneficiaryView;
    private RecevierDetailView receiverDetailView;
    private PaymentDetailsView paymentDetailsView;
    private BotKeyView botKeyView;
    private MPinView mPinView;


    private LinearLayout selectionBar;
    private ImageView selectedIcon;
    private TextView selectedTitle;
    private CustomViewManager customViewManager;

    public CustomViewManager(CustomIME customIME) {
        mCustomIme = customIME;
        customViewManager = this;
    }

    public CustomIME getContext() {
        return mCustomIme;
    }

    public void addTopBarViewOnKeyboarBoardTop() {
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        LinearLayout topBarView = (LinearLayout) layoutInflater.inflate(R.layout.top_bar_view_layout, null);

        ImageButton keyboardViewButton = topBarView.findViewById(R.id.gotoKeyboard);
        ImageButton imgBtnPay = topBarView.findViewById(R.id.img_btn_pay);
        ImageButton imgBtnBot = topBarView.findViewById(R.id.img_btn_chat);
        ImageButton imgHistory = topBarView.findViewById(R.id.img_btn_history);

        selectionBar = topBarView.findViewById(R.id.selectionBar);
        selectedIcon = topBarView.findViewById(R.id.selectedIcon);
        selectedTitle = topBarView.findViewById(R.id.selectedTitle);

        keyboardViewButton.setOnClickListener(this);
        imgBtnPay.setOnClickListener(this);
        topBarView.setOnClickListener(this);
        imgHistory.setOnClickListener(this);
        imgBtnBot.setOnClickListener(this);
        if (mCustomIme.isInputViewShown()) {
            mCustomIme.hideWindow();
        }
        try {
            mCustomIme.setTopBar(topBarView);
        } catch (Exception e) {
            Util.logException(TAG, "addTopBarViewOnKeyboarBoardTop", e);
        }
    }

    @Override
    public void onClick(View v) {
        if (!v.isEnabled()) return;

        AudioManager am = (AudioManager) mCustomIme.getSystemService(Context.AUDIO_SERVICE);
        try {
            if (am != null) am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, 0.9f);
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }

        switch (v.getId()) {
            case R.id.img_btn_pay:
                showMPinView();
                break;
            case R.id.img_btn_history:
                showTransactionView();
                break;
            case R.id.img_btn_chat:
                showBotView();
                break;
            case R.id.top_bar_root:
                try {
                    mCustomIme.restoreInputTarget();
                } catch (Exception e) {
                    Util.logException(TAG, "onClick", e);
                }
                break;
            case R.id.gotoKeyboard:
                restoreToSelectionBar();
                break;
        }
    }

    private void destroyViews() {
        if (payView != null) payView.destroy();
        payView = null;

        if (selectToPayView != null) selectToPayView.destroy();
        selectToPayView = null;

        if (transactionView != null) transactionView.destroy();
        selectToPayView = null;

        if (receiverDetailView != null) receiverDetailView.destroy();
        receiverDetailView = null;

        if (addBeneficiaryView != null) addBeneficiaryView.destroy();
        addBeneficiaryView = null;

        if (paymentDetailsView != null) paymentDetailsView.destroy();
        paymentDetailsView = null;

        if (botKeyView != null) botKeyView.destroy();
        botKeyView = null;

        if (mPinView != null) mPinView.destroy();
        mPinView = null;


    }

    private void slideInSelectedBar(String title, int icon) {
        selectedTitle.setText(title);
        selectedIcon.setImageDrawable(ContextCompat.getDrawable(mCustomIme, icon));
        selectionBar.setVisibility(View.GONE);
    }

    public void restoreToSelectionBar() {
        selectionBar.setVisibility(View.VISIBLE);
        try {
            mCustomIme.restoreInputTarget();
            mCustomIme.showKeyboardView();
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }
    }

    public void showPayView(final PayTo payTo) {
        destroyViews();
        payView = PayView.getInstance(customViewManager, payTo);
        try {
            slideInSelectedBar("Pay", R.drawable.demo_icon);
            Util.showView(mCustomIme, payView.getView());
            mCustomIme.showCustomView(payView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Pay View", e);
        }
    }

    public void showSelectToPayView() {
        destroyViews();
        addTopBarViewOnKeyboarBoardTop();
        slideInSelectedBar("Select To Pay", R.drawable.demo_icon);
        selectToPayView = SelectToPayView.getInstance(this);

        try {
            Util.showView(mCustomIme, selectToPayView.getView());
            mCustomIme.showCustomView(selectToPayView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }

    public void showTransactionView() {
        destroyViews();
        slideInSelectedBar("Select To Pay", R.drawable.demo_icon);
        transactionView = TransactionView.getInstance(mCustomIme);

        try {
            Util.showView(mCustomIme, transactionView.getView());
            mCustomIme.showCustomView(transactionView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }

    public void showAddBeneficaryView() {
        destroyViews();
        slideInSelectedBar("Select To Pay", R.drawable.demo_icon);
        addBeneficiaryView = AddBeneficiaryView.getInstance(this);
        try {
            Util.showView(mCustomIme, addBeneficiaryView.getView());
            mCustomIme.showCustomView(addBeneficiaryView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }

    private void showReceiverDetailView() {
        destroyViews();
        slideInSelectedBar("Receiver Detail", R.drawable.demo_icon);
        receiverDetailView = RecevierDetailView.getInstance(mCustomIme);

        try {
            mCustomIme.showCustomView(receiverDetailView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }

    public void showBotView() {
        destroyViews();
        slideInSelectedBar("Chat", R.drawable.demo_icon);
        botKeyView = BotKeyView.getInstance(mCustomIme);

        try {
            mCustomIme.showCustomView(botKeyView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }

    public void showPaymentDetailsView(Transaction transaction) {
        destroyViews();
        slideInSelectedBar("Chat", R.drawable.demo_icon);
        paymentDetailsView = PaymentDetailsView.getInstance(this, transaction);

        try {
            Util.showView(mCustomIme, paymentDetailsView.getView());
            mCustomIme.showCustomView(paymentDetailsView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }

    private void showMPinView() {
        destroyViews();
        slideInSelectedBar("Chat", R.drawable.demo_icon);
        mPinView = MPinView.getInstance(this);

        try {
            mCustomIme.setTopBar(mPinView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Select To Pay", e);
        }
    }
}
