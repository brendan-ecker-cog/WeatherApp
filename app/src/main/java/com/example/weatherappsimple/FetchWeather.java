package com.example.weatherappsimple;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Class to communicate with the network util to get API data
 */
public class FetchWeather extends AsyncTask<String, Void, String> {

    Context context;
    private WeatherDatabase mDb;

    /**
     * Constructor
     * @param con
     */
    public FetchWeather(Context con){
        context = con;
    }

    /**
     * Does in the background when it is possible
     * @param strings The information needed for the NetworkUtil method.
     * @return The data from the api
     */
    @Override
    protected String doInBackground(String... strings) {
        //Get data from the api
        String data = NetworkUtil.getWeatherInfo(strings[0]);
        Intent intent = new Intent(context, WeatherActivity.class);
        mDb = WeatherDatabase.getInstance(context);

        // Get data out of the json object from the API
        try {
            JSONObject json = new JSONObject(data);
            JSONObject location = json.getJSONObject("location");
            String name = location.getString("name");
            intent.putExtra("Name", name);
            String region = location.getString("region");
            JSONObject current = json.getJSONObject("current");
            double tempC = (current.getDouble("temp_c"));
            double tempF = (current.getDouble("temp_f"));
            //Checked to see if its day or night out.
            JSONObject condition = current.getJSONObject("condition");
            String sky = condition.getString("text");
            int windMPH = (current.getInt("wind_mph"));
            int humidity = (current.getInt("humidity"));
            double feelLikeC = (current.getDouble("feelslike_c"));
            double feelLikeF = (current.getDouble("feelslike_f"));
            //Insert city to the DB (If the city has the same name it will crash)
            mDb.weatherDao().insertCity(new City(name, region, tempC, tempF, sky, windMPH, humidity, feelLikeC, feelLikeF));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Starts next activity.
        context.startActivity(intent);
        return data;
    }
}
