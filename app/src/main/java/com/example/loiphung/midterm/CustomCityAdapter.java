package com.example.loiphung.midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by LoiPhung on 3/12/18.
 */

public class CustomCityAdapter extends ArrayAdapter<City> {


    public CustomCityAdapter(Context context, int resource, ArrayList<City> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = this.getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_item, parent, false);

        TextView cityText = convertView.findViewById(R.id.cityText);
        cityText.setText(city.toString());


        return convertView;
    }

}
