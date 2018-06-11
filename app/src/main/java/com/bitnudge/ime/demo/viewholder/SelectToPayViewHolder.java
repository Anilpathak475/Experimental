package com.bitnudge.ime.demo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitnudge.ime.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectToPayViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_name)
    public TextView txtName;

    @BindView(R.id.layout_parent)
    public LinearLayout layoutParent;

    @BindView(R.id.txt_card_no)
    public TextView txtCardName;

    @BindView(R.id.img_info)
    public ImageView imgInfo;

    @BindView(R.id.img_icon)
    public ImageView imgIcon;


    public SelectToPayViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
