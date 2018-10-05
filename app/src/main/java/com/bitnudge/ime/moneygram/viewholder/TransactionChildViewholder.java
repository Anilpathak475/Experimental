package com.bitnudge.ime.moneygram.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitnudge.ime.moneygram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionChildViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_name)
    public TextView txtName;

    @BindView(R.id.txt_amount)
    public TextView txtAmount;

    @BindView(R.id.txt_date)
    public TextView txtDate;

    @BindView(R.id.txt_status)
    public TextView txtStatus;

    @BindView(R.id.layout_parent)
    public CardView cardViewParent;

    public TransactionChildViewholder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}