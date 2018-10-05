/*
 * Copyright (C) 2015 Tomás Ruiz-López.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bitnudge.ime.moneygram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.libs.CalenderUtil;
import com.bitnudge.ime.moneygram.model.Transaction;
import com.bitnudge.ime.moneygram.model.TransactionDetailed;
import com.bitnudge.ime.moneygram.viewholder.TransactionChildViewholder;
import com.bitnudge.ime.moneygram.viewholder.TransactionParentViewHolder;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.List;

public class TransactionAdapter extends SectionedRecyclerViewAdapter<TransactionParentViewHolder,
        TransactionChildViewholder, TransactionParentViewHolder> {

    private Context context = null;
    private List<TransactionDetailed> transactionDetails;
    private ClickListener clickListener;

    public TransactionAdapter(List<TransactionDetailed> transactionDetails, ClickListener clickListener) {
        this.transactionDetails = transactionDetails;
        this.clickListener = clickListener;
    }

    @Override
    protected int getSectionCount() {
        return transactionDetails.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return transactionDetails.get(section).getTransactions().size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }


    @Override
    protected TransactionParentViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_transaction_parent, parent, false);
        return new TransactionParentViewHolder(view);
    }

    @Override
    protected TransactionParentViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected TransactionChildViewholder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tranaction_child, parent, false);
        return new TransactionChildViewholder(view);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(TransactionParentViewHolder holder, int section) {
        holder.txtPeriod.setText(transactionDetails.get(section).getDuration());
    }

    @Override
    protected void onBindSectionFooterViewHolder(TransactionParentViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(TransactionChildViewholder holder, int section, int position) {
        final Transaction transaction = transactionDetails.get(section).getTransactions().get(position);
        holder.txtName.setText(String.format("%s %s", transaction.getSenderFirstName(), transaction.getSenderLastName()));
        holder.txtDate.setText(String.format("%s", CalenderUtil.getFormatedDateFromDate(transaction.getDateInitiated())));
        holder.txtStatus.setText(String.format("%s ", transaction.getStatus()));
        holder.txtAmount.setText(String.format("%s %s", transaction.getSendCurrency(), transaction.getSendAmount()));
        holder.cardViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(transaction);
            }
        });
    }

    public interface ClickListener {
        void onClick(Transaction transaction);
    }
}
