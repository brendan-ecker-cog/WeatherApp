package com.example.weatherappsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Main activity of the Weather App Project
 */
public class MainActivity extends AppCompatActivity {
    private EditText cityText;
    static final String TAG = "WEATHER APP";

    /**
     * When the activity is created run this method.
     * @param savedInstanceState The state of the application IF saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityText = findViewById(R.id.edit_city);
    }

    /**
     * Fetch the weather from the city that was entered into the text view.
     * Also the on click action of the button
     * @param view view
     */
    public void fetchWeather(View view) {
        String query = cityText.getText().toString();
        Log.i(TAG, query);


        //Copied from who wrote it project (gets the connectivity to the internet for the api call)
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && query.length() != 0) {
            //Gets the data from API
            new FetchWeather(this).execute(query);
        }

    }

    /**
     * The onclick method to open up the city activity
     * @param view What called this method
     */
    public void getAllCities(View view) {
        Intent intent = new Intent(this, CityActivity.class);
        startActivity(intent);
    }
}