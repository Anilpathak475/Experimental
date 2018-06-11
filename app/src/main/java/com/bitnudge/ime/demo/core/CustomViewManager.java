package com.bitnudge.ime.demo.core;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.keyViews.AddBeneficiaryView;
import com.bitnudge.ime.demo.keyViews.BotKeyView;
import com.bitnudge.ime.demo.keyViews.MPinView;
import com.bitnudge.ime.demo.keyViews.MenuView;
import com.bitnudge.ime.demo.keyViews.PayView;
import com.bitnudge.ime.demo.keyViews.PaymentDetailsView;
import com.bitnudge.ime.demo.keyViews.RecevierDetailView;
import com.bitnudge.ime.demo.keyViews.SelectBeneficiaryView;
import com.bitnudge.ime.demo.keyViews.SelectedHeaderView;
import com.bitnudge.ime.demo.keyViews.SelectionView;
import com.bitnudge.ime.demo.keyViews.TransactionView;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.model.PayToContainer;
import com.bitnudge.ime.demo.model.Transaction;

/**
 * Created by Adhityan on 17/03/18.
 */

public class CustomViewManager {
    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;

    private KeyView view;
    private SelectionView selectionView;
    private SelectedHeaderView headerView;

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
        //showSelectedBar("MPin", R.drawable.moneygram_logo);
        headerView = SelectedHeaderView.getInstance(this, title, icon);

        try {
            mCustomIme.setTopBar(headerView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "showMPinView", e);
        }
    }

    public void showMPinView() {
        destroyViews();
        //showSelectedBar("MPin", R.drawable.moneygram_logo);
        view = MPinView.getInstance(this);

        try {
            mCustomIme.setTopBar(view.getView());
        } catch (Exception e) {
            Util.logException(TAG, "showMPinView", e);
        }
    }

    public void showPayView(final PayToContainer payTo) {
        destroyViews();
        showSelectedBar("Pay", R.drawable.moneygram_logo);

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
        showSelectedBar("Add Beneficiary", R.drawable.moneygram_logo);

        view = AddBeneficiaryView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    private void showReceiverDetailView() {
        destroyViews();
        showSelectedBar("Receiver Detail", R.drawable.moneygram_logo);

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
        showSelectedBar("Pay Status", R.drawable.moneygram_logo);

        view = PaymentDetailsView.getInstance(this, transaction);
        Util.showView(mCustomIme, view.getView());
    }

    public void showMenuView() {
        destroyViews();
        showSelectedBar("What do you want to do?", R.drawable.moneygram_logo);

        view = MenuView.getInstance(this);
        Util.showView(mCustomIme, view.getView());
    }

    public void showSelectBenefciaryView() {
        destroyViews();
        showSelectedBar("Who do you want to pay?", R.drawable.moneygram_logo);

        view = SelectBeneficiaryView.getInstance(this);
        try {
            mCustomIme.showCustomView(view.getView());
        }
        catch (Exception e) { Util.logException(TAG, "showSelectBenefciaryView", e); }
    }
}
