package com.bitnudge.ime.demo.keyViews;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.adapter.SelectToPayAdapter;
import com.bitnudge.ime.demo.core.CustomIME;
import com.bitnudge.ime.demo.modle.PayTo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectToPayView implements SelectToPayAdapter.ClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String TAG = this.getClass().getSimpleName();
    private CustomIME mCustomIme;
    private View v;


    private SelectToPayView(CustomIME context) {
        this.mCustomIme = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.layout_select_to_pay, null);
        ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SelectToPayAdapter(new ArrayList<PayTo>(), this));

    }

    public View getView() {
        return v;
    }

    public void destroy() {
        mCustomIme = null;
    }

    public static SelectToPayView getInstance(CustomIME context) {
        return new SelectToPayView(context);
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onInfoClick(int position) {

    }
}
