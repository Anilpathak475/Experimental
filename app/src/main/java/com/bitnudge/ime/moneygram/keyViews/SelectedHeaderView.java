package com.bitnudge.ime.moneygram.keyViews;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.bitnudge.ime.moneygram.core.CustomViewManager;
import com.bitnudge.ime.moneygram.interfaces.KeyView;
import com.bitnudge.ime.moneygram.libs.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectedHeaderView implements KeyView {
    private final String TAG = getClass().getName();

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private Boolean inProgress;
    private View v;

    @BindView(R.id.selectedIcon)
    ImageView selectedIcon;

    @BindView(R.id.selectedTitle)
    TextView selectedTitle;

    private SelectedHeaderView(final CustomViewManager customViewManager, String title, int icon) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.top_bar_header_layout, null);
        unbinder = ButterKnife.bind(this, v);

        selectedTitle.setText(title);
        selectedIcon.setImageDrawable(ContextCompat.getDrawable(mCustomIme, icon));

        inProgress = false;
    }

    public static SelectedHeaderView getInstance(CustomViewManager customViewManager, String title, int icon) {
        return new SelectedHeaderView(customViewManager, title, icon);
    }

    @OnClick(R.id.gotoKeyboard)
    public void onClick(View v) {
        if (!v.isEnabled()) return;
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);

        switch (v.getId()) {
            case R.id.gotoKeyboard: //selected
                customViewManager.showSelectionBar();
                break;
        }
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
