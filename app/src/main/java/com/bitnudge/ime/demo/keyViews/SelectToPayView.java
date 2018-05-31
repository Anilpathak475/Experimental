package com.bitnudge.ime.demo.keyViews;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.SelectToPayAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.modle.Card;
import com.bitnudge.ime.demo.modle.PayTo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectToPayView implements SelectToPayAdapter.ClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.img_add_payee)
    ImageView imgAddPayee;

    @BindView(R.id.layout_parent)
    LinearLayout layoutParent;

    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;
    private List<PayTo> payess;

    private SelectToPayView(CustomViewManager customViewManager) {
        mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;
        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_select_to_pay, null);
        ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(mCustomIme));
        payess = new ArrayList<>();

        payess.add(getPayTo("Anil Pathak", "1234 5678 9012 1223", R.drawable.icici));
        payess.add(getPayTo("Braj B", "1234 5678 9012 4545", R.drawable.rbs));
        payess.add(getPayTo("Yug", "1234 5678 9012 9876", R.drawable.hdfc));
        payess.add(getPayTo("Braj b", "1234 5678 9012 5564", R.drawable.hsbc));
        recyclerView.setAdapter(new SelectToPayAdapter(payess, this));

    }

    public static SelectToPayView getInstance(CustomViewManager context) {
        return new SelectToPayView(context);
    }

    @OnClick(R.id.img_add_payee)
    void onClickAdd() {
        customViewManager.showAddBeneficaryView();
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
        recyclerView = null;
        edtSearch = null;
        imgAddPayee = null;
        layoutParent = null;
    }

    @OnClick(R.id.img_back)
    void onClickBack() {
        customViewManager.showSelectToPayView();
    }

    @Override
    public void onItemClick(int position) {
        layoutParent.startAnimation(Util.hideView());
        layoutParent.setVisibility(View.GONE);
        customViewManager.showPayView(payess.get(position));
    }

    @Override
    public void onInfoClick(int position) {

    }

    private PayTo getPayTo(String name, String cardNo, int id) {
        PayTo payTo = new PayTo();
        payTo.setName(name);
        Card card = new Card(cardNo, id);
        payTo.setCard(card);
        return payTo;
    }
}
