package com.example.weatherappsimple;


import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class to communicate with the internet and the API
 */
public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();
    // Base URL for Books API.
    private static final String BOOK_BASE_URL = "https://api.weatherapi.com/v1/current.json?";
    // Parameter for the search string.
    private static final String Q_PARAM = "q";
    private static final String APPID_PARAM = "key";


    /**
     * Gets the weather information from the API and return it.
     *
     * @param query The information needed for the query (City name)
     * @return The weather information from the API
     */
    static String getWeatherInfo(String query) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String weatherString = null;
        String q = query;

        //Build URI
        Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(APPID_PARAM, "a0fa52425144497a93e140250221807")
                .appendQueryParameter(Q_PARAM, q)
                .build();
        try {
            //Connect
            URL reqURL = new URL(builtURI.toString());
            Log.i(TAG, reqURL.toString());
            urlConnection = (HttpURLConnection) reqURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            

            //Get the data back from API
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            
            //Build the data into a string from the API
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }

            weatherString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Shut off connection and reader
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return weatherString;
    }
}