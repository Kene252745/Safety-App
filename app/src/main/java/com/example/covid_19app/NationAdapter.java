package com.example.covid_19app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NationAdapter extends ArrayAdapter<NationDisplay> {
    private  Context context;
    private List<NationDisplay> nationList;
    private List<NationDisplay> nationListSelected;
    public NationAdapter (Context context, List<NationDisplay> nationList) {
        super(context, R.layout.nations, nationList);

        this.context = context;
        this.nationList = nationList;
        this.nationListSelected = nationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nations, null, true);
        TextView countryNames = view.findViewById(R.id.countryNames);
        ImageView countryImages = view.findViewById(R.id.countryImages);

        countryNames.setText(nationListSelected.get(position).getCountry());
        Glide.with(context).load(nationListSelected.get(position).getFlag()).into(countryImages);

        return view;
    }

    @Override
    public int getCount() {
        return nationListSelected.size();
    }

    @Nullable
    @Override
    public NationDisplay getItem(int position) {
        return nationListSelected.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = nationList.size();
                    filterResults.values = nationList;

                }else{
                    List<NationDisplay> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(NationDisplay itemsModel:nationList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                nationListSelected = (List<NationDisplay>) results.values;
                NationCounts.nationList = (List<NationDisplay>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


}
