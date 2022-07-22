package com.example.weatherappsimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * The City activity that shows all the cities in the Database and the temperature
 */
public class CityActivity extends AppCompatActivity {
    private RecyclerView recView;
    private WeatherDatabase mDb;
    private static final String TAG = CityActivity.class.getSimpleName();

    /**
     * This method runs when this activity is started.
     * @param savedInstanceState    The state of the application IF saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        recView = findViewById(R.id.recView);
        int gridCount = getResources().getInteger(R.integer.grid_column_count);
        recView.setLayoutManager(new GridLayoutManager(this, gridCount));

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
                        Log.i(TAG, "City -> City View");
                        CityAdapter adapter = new CityAdapter(cities);
                        recView.setAdapter(adapter);
                    }
                });
            }
        });

    }
}