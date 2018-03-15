package com.example.loiphung.midterm;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    String URL, state, city;
    public static Weather thisWeather = new Weather();
    public static ArrayList<City> cityArrayList = new ArrayList<City>();

    public static String urlC = "";

    //http://autocomplete.wunderground.com/aq?query=query


    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state = "CA";

        URL = "http://api.wunderground.com/api/f6a53adc699e9f99/conditions/q/" + state + "/San_Francisco.json";



        findViewById(R.id.searchIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListView cityListView = findViewById(R.id.city_listView);
                EditText cityField = findViewById(R.id.cityEditText);
                String search = cityField.getText().toString();
                new GetCityAsync(MainActivity.this, cityListView).execute("http://autocomplete.wunderground.com/aq?query=" + search);
                CustomCityAdapter adapter = new CustomCityAdapter(MainActivity.this, R.layout.city_item, cityArrayList);
                cityListView.setAdapter(adapter);


            }
        });

        final ListView cityListView = findViewById(R.id.city_listView);
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String zmw = "";
                zmw = cityArrayList.get(position).getZmw();
                if(zmw == ""){
                    zmw = "33951.3.99999";
                }
                urlC = ("http://api.wunderground.com/api/f6a53adc699e9f99/conditions/q/zmw:"+ zmw + ".json");

                new GetDataAsync(MainActivity.this).execute(urlC);


                Log.d("Item clicked Position ", position + " :" + thisWeather.toString() );

                Intent i = new Intent(MainActivity.this, WeatherDetailActivity.class);

                startActivity(i);



            }
        });





        //new GetDataAsync(MainActivity.this).execute(URL);




    }//end oncreate


    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }


}//end class
