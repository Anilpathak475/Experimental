package com.bitnudge.ime.demo.keyViews;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.BeneficiaryAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.interfaces.AdapterClickListener;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.model.Bill;
import com.bitnudge.ime.demo.model.PayToContainer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectBeneficiaryView implements KeyView, AdapterClickListener {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.layout_parent)
    LinearLayout layoutParent;

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private View v;

    private List<PayToContainer> payees;
    private Boolean inProgress;

    private SelectBeneficiaryView(CustomViewManager customViewManager) {
        mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_select_beneficiary_view, null);
        unbinder = ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(mCustomIme));

        payees = new ArrayList<>();
        payees.add(new PayToContainer("Giovanna Gilyard", "1234 5678 9012 1223", R.drawable.hdfc));
        payees.add(new PayToContainer("Alec Amend", "1234 5678 9012 4545", R.drawable.rbs));
        payees.add(new PayToContainer("Jeffery Jesus", "5129 5678 9012 9876", R.drawable.hdfc));
        payees.add(new PayToContainer("Tyler Timberlake", "1234 5678 9012 5564", R.drawable.hsbc));
        payees.add(new PayToContainer("Vernia Vallo", "1289 5678 9012 5564", R.drawable.icici));
        payees.add(new PayToContainer("Janine Jeter", "1728 5678 9012 5564", R.drawable.rbs));
        payees.add(new PayToContainer("Kraig Kitzmiller", "1234 5678 9012 5564", R.drawable.hdfc));
        payees.add(new PayToContainer("Merrilee Masten", "1820 5678 9012 5564", R.drawable.hsbc));
        payees.add(new PayToContainer("Reyes Reeder", "1234 5678 9012 5564", R.drawable.icici));
        payees.add(new PayToContainer("Clark Chien", "4783 5678 9012 5564", R.drawable.icici));
        recyclerView.setAdapter(new BeneficiaryAdapter(payees, this));

        inProgress = false;
    }

    public static SelectBeneficiaryView getInstance(CustomViewManager context) {
        return new SelectBeneficiaryView(context);
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

    @OnClick(R.id.img_add_payee)
    void onClickAdd() {
        Util.makeTapSound(mCustomIme);
        customViewManager.showAddBeneficaryView();
    }

    @OnClick(R.id.img_back)
    void onClickBack() {
        if(inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showMenuView();
    }

    @Override
    public void onItemClick(int position) {
        if(inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        Util.hideView(mCustomIme, layoutParent);
        customViewManager.showPayView(payees.get(position));
    }

    @Override
    public void onInfoClick(int position) {

    }

    private PayToContainer getPayTo(String name, String cardNo, int id) {
        return new PayToContainer(name, cardNo, id);
    }
}
