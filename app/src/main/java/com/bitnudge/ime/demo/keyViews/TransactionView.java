package com.bitnudge.ime.demo.keyViews;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.TransactionAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.core.CustomViewManager;
import com.bitnudge.ime.demo.interfaces.KeyView;
import com.bitnudge.ime.demo.libs.Util;
import com.bitnudge.ime.demo.model.Card;
import com.bitnudge.ime.demo.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TransactionView implements KeyView, TransactionAdapter.ClickListener {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.spn_status)
    Spinner spnStatus;

    @BindView(R.id.img_back)
    ImageView imgBack;

    private Unbinder unbinder;
    private CustomIME mCustomIme;
    private CustomViewManager customViewManager;
    private boolean inProgress;
    private View v;


    private TransactionView(CustomViewManager customViewManager) {
        this.mCustomIme = customViewManager.getContext();
        this.customViewManager = customViewManager;

        LayoutInflater layoutInflater = LayoutInflater.from(this.mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_transaction, null);
        unbinder = ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.mCustomIme));
        recyclerView.setAdapter(new TransactionAdapter(new ArrayList<Transaction>(), this));

        List<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Failed");
        status.add("Successful");
        spnStatus.setAdapter(new ArrayAdapter<String>(mCustomIme, android.R.layout.simple_dropdown_item_1line, status));

        inProgress = false;
        spnStatus.setEnabled(false);
    }

    public static TransactionView getInstance(CustomViewManager customViewManager) {
        return new TransactionView(customViewManager);
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

    @OnClick(R.id.img_back)
    void onClickBack() {
        if (inProgress) return;
        inProgress = true;

        Util.makeTapSound(mCustomIme);
        customViewManager.showMenuView();
    }

    @Override
    public void onItemClick(int position) {
        if (inProgress) return;
        inProgress = true;

        Transaction transaction = new Transaction();
        transaction.setName("Giovanna Gilyard");
        transaction.setCard(new Card("1234", 1));
        transaction.setAmount("AED 1500");
        transaction.setNotes("-");
        transaction.setStatus("Successful");
        transaction.setDate(new Date());
        transaction.setCurrency("");

        Util.makeTapSound(mCustomIme);
        customViewManager.showPaymentDetailsView(transaction);
    }
}
