package com.example.weatherappsimple;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Class to communicate with the network util to get API data
 */
public class FetchWeather extends AsyncTask<String, Void, String> {

    Context context;

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
        //Gives data to next activity
        if(data != null) {
            intent.putExtra("Data", data);
        } else {
            intent.putExtra("Data", "Not Found");
        }
        //Starts next activity.
        context.startActivity(intent);
        return data;
    }
}
