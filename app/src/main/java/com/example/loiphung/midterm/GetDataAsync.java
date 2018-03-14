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

public class GetDataAsync extends AsyncTask<String, Integer, Weather> {

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
    protected Weather doInBackground(String... params) {

        HttpURLConnection connection = null;
        Weather a = new Weather();

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");


                JSONObject root = new JSONObject(json);

                    JSONObject weatherJsonObject = root.optJSONObject("conditions");
                    JSONObject sourceJsonObject = weatherJsonObject.optJSONObject("current_observations");


                a.setTemp_f(sourceJsonObject.optString("temp_f"));
                a.setWindGust(sourceJsonObject.optString("wind_gust_mph"));
                a.setWeather(sourceJsonObject.optString("weather"));
                a.setFeelslike_f(sourceJsonObject.optString("feelslike_f"));
                a.setIconUrl(sourceJsonObject.optString("icon_url"));
                a.setRelativeHumidity(sourceJsonObject.optString("relative_humidity"));




                Log.d("tempf" , a.getTemp_f());
                Log.d("feels Like" , a.getFeelslike_f());
                Log.d("URL Icon" , a.getIconUrl());
                Log.d("relative Humdity" , a.getRelativeHumidity());


                    return a;

            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connections
        }

        return a;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        pb.setProgress(values[0]);


    }

    protected void onPostExecute(Weather result) {

        /*if (pb.getProgress() != pb.getMax()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pb.setProgress(pb.getMax());
        }

        MainActivity.articlesArrayList = result;
        CustomArticleAdapter adapter = new CustomArticleAdapter(context, R.layout.article_row, result);
        listview.setAdapter(adapter);

        Log.d("result", ""+ result);
        Log.d("articleArrayList", " "+ MainActivity.articlesArrayList);*/

        MainActivity.thisWeather = result;


    }
}