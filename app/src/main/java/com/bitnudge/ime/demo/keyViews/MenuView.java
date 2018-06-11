package com.bitnudge.ime.demo.keyViews;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.libs.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MenuView implements KeyView {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.layout_parent)
    LinearLayout layoutParent;

    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Unbinder unbinder;
    private View v;

    private MenuView(CustomViewManager customViewManager) {
        this.customViewManager = customViewManager;
        this.mCustomIme = customViewManager.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_menu_view, null);
        unbinder = ButterKnife.bind(this, v);
    }

    public static MenuView getInstance(CustomViewManager customViewManager) {
        return new MenuView(customViewManager);
    }

    @OnClick(R.id.layout_pay)
    void onClickPay() {
        Util.makeTapSound(mCustomIme);
        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showSelectBenefciaryView();
    }

    @OnClick(R.id.layout_receive)
    void onClickReceive() {
        Util.makeTapSound(mCustomIme);
    }

    @OnClick(R.id.layout_info)
    void onClickInfo() {
        Util.makeTapSound(mCustomIme);
    }

    @OnClick(R.id.layout_history)
    void onClickHistory() {
        Util.makeTapSound(mCustomIme);
        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showTransactionView();
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
