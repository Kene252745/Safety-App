package com.example.covid_19app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class NationAdapter extends ArrayAdapter<NationDisplay> {
    private  Context context;
    private List<NationDisplay> nationList;
    public NationAdapter (Context context, List<NationDisplay> nationList) {
        super(context, R.layout.nations, nationList);

        this.context = context;
        this.nationList = nationList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nations, null, true);
        TextView countryNames = view.findViewById(R.id.countryNames);
        ImageView countryImages = view.findViewById(R.id.countryImages);

        countryNames.setText(nationList.get(position).getCountry());
        Glide.with(context).load(nationList.get(position).getFlag()).into(countryImages);

        return view;
    }
}
