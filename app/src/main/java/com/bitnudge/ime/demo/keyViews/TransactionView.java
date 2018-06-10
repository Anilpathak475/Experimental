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
import com.bitnudge.ime.demo.modle.Card;
import com.bitnudge.ime.demo.modle.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionView implements TransactionAdapter.ClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.spn_status)
    Spinner spnStatus;

    @BindView(R.id.img_back)
    ImageView imgBack;

    private String TAG = this.getClass().getSimpleName();
    private CustomViewManager viewManager;
    private CustomIME mCustomIme;
    private View v;


    private TransactionView(CustomViewManager viewManager) {
        this.viewManager = viewManager;
        this.mCustomIme = viewManager.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(this.mCustomIme);
        v = layoutInflater.inflate(R.layout.layout_transaction, null);
        ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.mCustomIme));
        recyclerView.setAdapter(new TransactionAdapter(new ArrayList<Transaction>(), this));

        List<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Failed");
        status.add("Successful");
        spnStatus.setAdapter(new ArrayAdapter<>(mCustomIme, android.R.layout.simple_dropdown_item_1line, status));
    }

    public static TransactionView getInstance(CustomViewManager viewManager) {
        return new TransactionView(viewManager);
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
        recyclerView = null;
        edtSearch = null;
        spnStatus = null;
        imgBack = null;
    }

    @Override
    public void onItemClick(int position) {
        Transaction transaction = new Transaction();
        transaction.setName("Shailesh Tiwari");
        transaction.setCard(new Card("1234", 1));
        transaction.setAmount("1000");
        transaction.setNotes("-");
        transaction.setStatus("Successful");
        transaction.setDate(new Date());
        transaction.setCurrency("USD");
        viewManager.showPaymentDetailsView(transaction);
    }
}
