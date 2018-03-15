package com.example.loiphung.midterm;

/**
 * Created by LoiPhung on 3/12/18.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by LoiPhung on 2/26/18.
 */

public class GetDataAsync extends AsyncTask<String, Integer, ArrayList<Weather> > {

    private ProgressDialog dialog;
    AlertDialog alert;
    AlertDialog.Builder builder;
    ProgressBar pb;
    ListView listview;
    Context context;

    public GetDataAsync(Context activity) {

        //this.listview = listView;
        context = activity;

    }

    @Override
    protected void onPreExecute() {
        //dialog.setMessage("Loading sources");
        //dialog.show();


    }

    @Override
    protected ArrayList<Weather>  doInBackground(String... params) {

        HttpURLConnection connection = null;
        ArrayList<Weather> arraylist = new ArrayList<Weather>();
        Weather a = new Weather();


        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");


                JSONObject root = new JSONObject(json);

                JSONObject weatherJsonObject = root.optJSONObject("current_observation");


                a.setTemp_f(weatherJsonObject.optString("temp_f"));
                a.setWindGust(weatherJsonObject.optString("wind_gust_mph"));
                a.setWeather(weatherJsonObject.optString("weather"));
                a.setFeelslike_f(weatherJsonObject.optString("feelslike_string"));
                a.setIconUrl(weatherJsonObject.optString("icon_url"));
                a.setRelativeHumidity(weatherJsonObject.optString("relative_humidity"));
                a.setObservationTime(weatherJsonObject.optString("observation_time"));
                a.setVisibilityMi("visibility");



                Log.d("Async weather", " ." + weatherJsonObject.optString("weather"));
                //Log.d("Source", " ." + sourceJsonObject.optString("weather"));
                Log.d("Async tempf" , a.getTemp_f());
                Log.d("Async feels Like" , a.getFeelslike_f());
                Log.d("Async URL Icon" , a.getIconUrl());
                Log.d("Async relative Humdity" , a.getRelativeHumidity());

                WeatherDetailActivity.weatherArrayList.add(a);
                arraylist.add(a);


            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connections
        }


        return arraylist;
    }



    protected void onPostExecute(ArrayList<Weather>  result) {


        WeatherDetailActivity.weatherArrayList = result;

        Log.d("result", ""+ result);
        Log.d("result size", ""+ result.size());


        //Log.d("articleArrayList", " "+ WeatherDetailActivity.weatherArrayList.get(0).getWeather());
        Log.d("articleArrayList size", " "+ WeatherDetailActivity.weatherArrayList.size());



    }
}