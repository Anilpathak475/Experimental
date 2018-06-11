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
import com.bitnudge.ime.demo.model.Country;

import java.util.List;

public class CountrySpinnerAdapter extends ArrayAdapter<Country> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final int mResource;
    private List<Country> countries;

    public CountrySpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                                 @NonNull List<Country> countries) {
        super(context, resource, countries);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        this.countries = countries;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);
        Country country = countries.get(position);
        TextView textView = view.findViewById(R.id.txt_card_no);
        ImageView imgCountry = view.findViewById(R.id.img_country_icon);
        textView.setText(country.getName());
        imgCountry.setBackground(mContext.getResources().getDrawable(country.getId()));
        return view;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.layout_selected_country_item, parent, false);
        ImageView imgCountry = view.findViewById(R.id.img_country_icon);
        imgCountry.setBackground(mContext.getResources().getDrawable(countries.get(position).getId()));
        return view;
    }

    @Override
    public int getCount() {
        return countries.size();
    }
}