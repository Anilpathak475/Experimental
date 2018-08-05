package com.bitnudge.ime.moneygram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.interfaces.AdapterClickListener;
import com.bitnudge.ime.moneygram.model.Bill;
import com.bitnudge.ime.moneygram.model.PayToContainer;
import com.bitnudge.ime.moneygram.viewholder.SelectToPayViewHolder;

import java.util.List;

public class BeneficiaryAdapter extends RecyclerView.Adapter<SelectToPayViewHolder> {
    private AdapterClickListener clickListener;
    private List<PayToContainer> pays;
    private Context context;

    public BeneficiaryAdapter(List<PayToContainer> pays, AdapterClickListener clickListener) {
        this.clickListener = clickListener;
        this.pays = pays;
    }

    @NonNull
    @Override
    public SelectToPayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_beneficiary_view_holder, parent, false);
        return new SelectToPayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectToPayViewHolder holder, final int position) {
        PayToContainer payTo = pays.get(position);
        holder.txtName.setText(payTo.getName());
        holder.txtCardName.setText(payTo.getCard().getCardNo());
        holder.imgIcon.setBackground(context.getResources().getDrawable(payTo.getCard().getDrawableId()));

        holder.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onInfoClick(position);
            }
        });
        holder.layoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pays.size();
    }
}