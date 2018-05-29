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
import com.bitnudge.ime.demo.keyViews.PayView;
import com.bitnudge.ime.demo.libs.Util;

/**
 * Created by Adhityan on 17/03/18.
 */

public class CustomViewManager implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();

    private CustomIME mCustomIme;
    private PayView payView;

    private LinearLayout selectionBar;
    private ImageView selectedIcon;
    private TextView selectedTitle;


    public CustomViewManager(CustomIME customIME) {
        mCustomIme = customIME;
    }

    public void addTopBarViewOnKeyboarBoardTop() {
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        LinearLayout topBarView = (LinearLayout) layoutInflater.inflate(R.layout.top_bar_view_layout, null);

        ImageButton keyboardViewButton = topBarView.findViewById(R.id.gotoKeyboard);
        ImageButton imgBtnPay = topBarView.findViewById(R.id.img_btn_pay);

        selectionBar = topBarView.findViewById(R.id.selectionBar);
        selectedIcon = topBarView.findViewById(R.id.selectedIcon);
        selectedTitle = topBarView.findViewById(R.id.selectedTitle);

        keyboardViewButton.setOnClickListener(this);
        imgBtnPay.setOnClickListener(this);
        topBarView.setOnClickListener(this);

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
                showPayView();
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

    private void slideInSelectedBar(String title, int icon) {
        selectedTitle.setText(title);
        selectedIcon.setImageDrawable(ContextCompat.getDrawable(mCustomIme, icon));
        selectionBar.setVisibility(View.GONE);
    }

    private void restoreToSelectionBar() {
        selectionBar.setVisibility(View.VISIBLE);

        try {
            mCustomIme.restoreInputTarget();
            mCustomIme.showKeyboardView();
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }
    }

    private void showPayView() {
        if (payView != null) payView.destroy();
        payView = null;

        slideInSelectedBar("Demo", R.drawable.demo_icon);
        payView = PayView.getInstance(mCustomIme);

        try {
            mCustomIme.showCustomView(payView.getView());
        } catch (Exception e) {
            Util.logException(TAG, "Pay View", e);
        }
    }
}
