package com.example.loiphung.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        TextView tv = findViewById(R.id.description);
        tv.setText(MainActivity.thisWeather.getDescription().indexOf(4));

    }
}
