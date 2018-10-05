package com.bitnudge.ime.moneygram.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bitnudge.ime.moneygram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionParentViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.txt_period)
    public TextView txtPeriod;


    public TransactionParentViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
