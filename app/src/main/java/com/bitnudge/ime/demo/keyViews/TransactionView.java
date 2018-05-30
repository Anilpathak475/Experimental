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
import com.bitnudge.ime.demo.modle.Transaction;

import java.util.ArrayList;
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
    private CustomIME mCustomIme;
    private View v;


    private TransactionView(CustomIME context) {
        this.mCustomIme = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.layout_transaction, null);
        ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new TransactionAdapter(new ArrayList<Transaction>(), this));

        List<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Failed");
        status.add("Successful");
        spnStatus.setAdapter(new ArrayAdapter<String>(mCustomIme, android.R.layout.simple_dropdown_item_1line, status));

    }

    public static TransactionView getInstance(CustomIME context) {
        return new TransactionView(context);
    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
    }

    @Override
    public void onItemClick(int position) {

    }
}
