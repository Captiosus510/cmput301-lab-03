package com.example.listycitylab3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    private Context context;
    private ArrayList<City> cities;
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
        this.context = context;
        this.cities = cities;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.content, null);

        }

        City city = cities.get(position);

        TextView cityText = convertView.findViewById(R.id.city_text);
        cityText.setText(city.getName());

        TextView provinceText = convertView.findViewById(R.id.province_text);
        provinceText.setText(city.getProvince());

        ImageView deleteIcon = convertView.findViewById(R.id.delete_icon);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cities.remove(position);
                notifyDataSetChanged();
            }
        });

        ConstraintLayout contentListItem = convertView.findViewById(R.id.content_list_item);
        contentListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click here
            }
        });

        return convertView;
    }
}
