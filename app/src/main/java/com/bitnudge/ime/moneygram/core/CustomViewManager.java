package com.bitnudge.ime.moneygram.core;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.interfaces.KeyView;
import com.bitnudge.ime.moneygram.keyViews.AddBeneficiaryView;
import com.bitnudge.ime.moneygram.keyViews.BotKeyView;
import com.bitnudge.ime.moneygram.keyViews.MPinView;
import com.bitnudge.ime.moneygram.keyViews.MenuView;
import com.bitnudge.ime.moneygram.keyViews.PayView;
import com.bitnudge.ime.moneygram.keyViews.PaymentDetailsView;
import com.bitnudge.ime.moneygram.keyViews.RecevierDetailView;
import com.bitnudge.ime.moneygram.keyViews.SelectBeneficiaryView;
import com.bitnudge.ime.moneygram.keyViews.SelectedHeaderView;
import com.bitnudge.ime.moneygram.keyViews.SelectionView;
import com.bitnudge.ime.moneygram.keyViews.TransactionView;
import com.bitnudge.ime.moneygram.libs.Util;
import com.bitnudge.ime.moneygram.model.PayToContainer;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.model.UserDetails;

/**
 * Created by Adhityan on 17/03/18.
 */

public class CustomViewManager {
    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;

    private KeyView view;
    private SelectionView selectionView;
    private SelectedHeaderView headerView;
    private UserDetails userDetails;

    public CustomViewManager(CustomIME customIME) {
        mCustomIme = customIME;
    }

    public CustomIME getContext() {
        return mCustomIme;
    }

    private void destroyViews() {
        if (view != null) view.destroy();
        view = null;

        if (selectionView != null) selectionView.destroy();
        selectionView = null;

        if (headerView != null) headerView.destroy();
        headerView = null;
    }

    public void showSelectionBar() {
        destroyViews();
        //if (mCustomIme.isInputViewShown()) mCustomIme.hideWindow();
        selectionView = SelectionView.getInstance(this);

        try {
            mCustomIme.setTopBar(selectionView.getView());
            mCustomIme.restoreInputTarget();
            mCustomIme.showKeyboardView();
            mCustomIme.onFinishInput();
        } catch (Exception e) {
            Util.logException(TAG, "showSelectionBar", e);
        }
    }

    private void showSelectedBar(String title, int icon) {
        destroyViews();
        //showSelectedBar("MPin", R.drawable.unimoni);
        headerView = SelectedHeaderView.getInstance(this, title, icon);

        try {
            mCustomIme.setTopBar(headerView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "showMPinView", e);
        }
    }

    public void showMPinView() {
        destroyViews();
        //showSelectedBar("MPin", R.drawable.unimoni);
        view = MPinView.getInstance(this);

        try {
            mCustomIme.setTopBar(view.getView());
        } catch (Exception e) {
            Util.logException(TAG, "showMPinView", e);
        }
    }

    public void showPayView(final PayToContainer payTo) {
        destroyViews();
        showSelectedBar("Pay", R.drawable.unimoni);

        view = PayView.getInstance(this, payTo);
        Util.showView(mCustomIme, view.getView());
    }

    public void showTransactionView() {
        destroyViews();
        showSelectedBar("History", R.drawable.transactions_icon);

        view = TransactionView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    public void showAddBeneficaryView() {
        destroyViews();
        showSelectedBar("Add Beneficiary", R.drawable.unimoni);

        view = AddBeneficiaryView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    private void showReceiverDetailView() {
        destroyViews();
        showSelectedBar("Receiver Detail", R.drawable.unimoni);

        view = RecevierDetailView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    public void showBotView() {
        destroyViews();
        showSelectedBar("Chat", R.drawable.bot_icon);

        view = BotKeyView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    public void showPaymentDetailsView(Transaction transaction) {
        destroyViews();
        showSelectedBar("Pay Status", R.drawable.unimoni);

        view = PaymentDetailsView.getInstance(this, transaction);
        Util.showView(mCustomIme, view.getView());
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public UserDetails getUserDetails() {
        if (userDetails != null) {
            return userDetails;
        } else {
            throw new NullPointerException();
        }
    }

    public void showMenuView() {
        destroyViews();
        showSelectedBar("What do you want to do?", R.drawable.unimoni);

        view = MenuView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    public void showSelectBenefciaryView() {
        destroyViews();
        showSelectedBar("Who do you want to pay?", R.drawable.unimoni);

        view = SelectBeneficiaryView.getInstance(this);
        try {
            mCustomIme.showCustomView(view.getView());
        } catch (Exception e) {
            Util.logException(TAG, "showSelectBenefciaryView", e);
        }
    }
}
