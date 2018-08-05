package com.bitnudge.ime.moneygram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.viewholder.TransactionViewHolder;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    private ClickListener clickListener;
    private List<Transaction> pays;

    public TransactionAdapter(List<Transaction> pays, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.pays = pays;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, final int position) {
        if (position % 2 == 0) {
            holder.layoutPeriod.setVisibility(View.VISIBLE);
            if (position == 0) {
                holder.txtPeriod.setText("Last Week");
            } else {
                holder.txtPeriod.setText("Last Month");
            }
        } else {
            holder.layoutPeriod.setVisibility(View.GONE);
        }
        holder.layoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }


    public interface ClickListener {
        void onItemClick(int position);
    }
}
