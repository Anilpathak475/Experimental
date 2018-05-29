package com.bitnudge.ime.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.modle.PayTo;
import com.bitnudge.ime.demo.viewholder.SelectToPayViewHolder;

import java.util.List;

public class SelectToPayAdapter extends RecyclerView.Adapter<SelectToPayViewHolder> {

    private ClickListener clickListener;
    private List<PayTo> pays;

    public SelectToPayAdapter(List<PayTo> pays, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.pays = pays;
    }

    @NonNull
    @Override
    public SelectToPayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_select_to_pay_view_holder, parent, false);
        return new SelectToPayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectToPayViewHolder holder, final int position) {
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
        return 8;
    }


    public interface ClickListener {
        void onItemClick(int position);

        void onInfoClick(int position);
    }
}
