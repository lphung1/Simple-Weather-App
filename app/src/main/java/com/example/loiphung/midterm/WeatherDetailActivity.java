package com.example.loiphung.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherDetailActivity extends AppCompatActivity {

    public static ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        new GetDataAsync(WeatherDetailActivity.this).execute(MainActivity.urlC);

        final ImageView imageView = findViewById(R.id.weatherIcon);
        final TextView city = findViewById(R.id.statusCity);
        final TextView weather = findViewById(R.id.weather);
        final TextView feelsLike = findViewById(R.id.feelslike);
        final TextView relativeHumidity = findViewById(R.id.relativehumidity);
        final TextView visibility = findViewById(R.id.visibility);
        final TextView wind = findViewById(R.id.wind);
        final TextView lastupdated = findViewById(R.id.lastupdated);


        //Log.d("Weather object", "" + MainActivity.weatherArrayList.get(0).getWeather());

        findViewById(R.id.statusCity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Running Get Data Async: ", "clicked ");
                String url, zmw = "";
                zmw = MainActivity.cityArrayList.get(0).getZmw();
                if(zmw == ""){
                    zmw = "33951.3.99999";
                }
                url = ("http://api.wunderground.com/api/f6a53adc699e9f99/conditions/q/zmw:"+ zmw + ".json");

                new GetDataAsync(WeatherDetailActivity.this).execute(url);

                if (weatherArrayList.size() != 0) {

                    Picasso.get().load(weatherArrayList.get(0).getIconUrl()).into(imageView);
                    Log.d("Picture URL", "" + MainActivity.thisWeather.getIconUrl());

                    //Log.d("Weather array list", " " + MainActivity.weatherArrayList.get(0).getWeather());
                    weather.setText(weatherArrayList.get(0).getWeather());
                    feelsLike.setText(weatherArrayList.get(0).feelslike_f);
                    relativeHumidity.setText(weatherArrayList.get(0).getRelativeHumidity());
                    visibility.setText(weatherArrayList.get(0).getVisibilityMi());
                    wind.setText(weatherArrayList.get(0).getWind());
                    lastupdated.setText(weatherArrayList.get(0).getObservationTime());

                }

            }
        });


    }//end on create



}
