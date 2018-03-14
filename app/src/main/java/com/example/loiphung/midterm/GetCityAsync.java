package com.example.loiphung.midterm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by LoiPhung on 3/12/18.
 */

public class GetCityAsync extends AsyncTask<String, Integer, ArrayList<City>> {

    ListView listView;
    Context context;

    GetCityAsync(Context c, ListView l){
        listView = l;
        context = c;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<City> cities) {
        super.onPostExecute(cities);

        MainActivity.cityArrayList = cities;



    }

    @Override
    protected ArrayList<City> doInBackground(String... params) {

        HttpURLConnection connection = null;
        ArrayList<City> result = new ArrayList<City>();
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");


                JSONObject root = new JSONObject(json);

                JSONArray articles = root.optJSONArray("RESULTS");

                for (int i = 0; i < articles.length(); i++) {
                    JSONObject cityJSONObject = articles.optJSONObject(i);
                    //JSONObject sourceJsonObject = cityJSONObject.optJSONObject("source");

                    City a = new City();

                    a.setCountry(cityJSONObject.getString("c"));
                    a.setCity(cityJSONObject.optString("name"));
                    a.setZmw(cityJSONObject.optString("zmw"));

                    Log.d("Country", a.getCountry());
                    Log.d("city", a.getCity());
                    Log.d("zmw", a.getZmw());


                    result.add(a);

                }
            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connections
        }
        return result;


    }//end do in background


}//end class
