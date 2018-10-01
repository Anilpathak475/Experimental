package com.bitnudge.ime.moneygram.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitnudge.ime.moneygram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.layout_parent)
    public CardView layoutParent;

    @BindView(R.id.layout_period)
    public LinearLayout layoutPeriod;

    @BindView(R.id.txt_period)
    public TextView txtPeriod;

    @BindView(R.id.txt_amount)
    TextView txtAmount;

    @BindView(R.id.txt_date)
    TextView txtDate;

    @BindView(R.id.txt_status)
    TextView txtStatus;

    public TransactionViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
