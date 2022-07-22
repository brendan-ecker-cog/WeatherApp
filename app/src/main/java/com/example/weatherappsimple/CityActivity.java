package com.example.weatherappsimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * The City activity that shows all the cities in the Database and the temperature
 */
public class CityActivity extends AppCompatActivity {
    private RecyclerView recView;
    private WeatherDatabase mDb;

    /**
     * This method runs when this activity is started.
     * @param savedInstanceState    The state of the application IF saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        //Get Database
        mDb = WeatherDatabase.getInstance(this);
        //Get Data
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                final List<City> cities = mDb.weatherDao().loadAllCity();
                runOnUiThread(new Runnable() {
                    //Set the UI with the data
                    @Override
                    public void run() {
                        CityAdapter adapter = new CityAdapter(cities);
                        recView.setAdapter(adapter);
                    }
                });
            }
        });

    }
}