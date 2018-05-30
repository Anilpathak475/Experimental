package com.bitnudge.ime.demo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.modle.Card;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Card> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<Card> cards;
    private final int mResource;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                          @NonNull List<Card> cards) {
        super(context, resource, cards);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        this.cards = cards;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);
        Card card = cards.get(position);
        TextView txtCountryName = view.findViewById(R.id.txt_card_no);
        ImageView imgIcon = view.findViewById(R.id.img_icon);
        txtCountryName.setText(extractLastFourDigits(card.getCardNo()));
        imgIcon.setBackground(mContext.getResources().getDrawable(card.getId()));
        return view;
    }

    private String extractLastFourDigits(String cardNo) {
        cardNo = cardNo.substring(cardNo.length() - 5, cardNo.length());
        return cardNo;
    }
}