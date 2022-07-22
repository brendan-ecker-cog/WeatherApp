package com.example.weatherappsimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;


public class WeatherActivity extends AppCompatActivity {
    private RecyclerView recView;
    private WeatherDatabase mDb;
    private static final String TAG = WeatherActivity.class.getSimpleName();

    /**
     * On create is called with this activity is started/resumed
     * @param savedInstanceState State of the application if saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        recView = findViewById(R.id.recyclerView);
        mDb = WeatherDatabase.getInstance(getApplicationContext());
        //Create Recycler View
        int gridCount = getResources().getInteger(R.integer.grid_column_count);
        recView.setLayoutManager(new GridLayoutManager(this, gridCount));
        //Get the extras from the previous activity (Data of the weather)
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        final City[] city = new City[1];
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                city[0] = mDb.weatherDao().loadCityByName(name);
                //Puts the data into the view.
                //Log.i(TAG, city[0].name);
                String[] info = city[0].getArray();

                ViewAdapter adapter = new ViewAdapter(info);
                recView.setAdapter(adapter);
            }
        });




        //Checks to see if the city was found and says not found if it wasn't and sets the text to the data if it is.
//        if(data.equals("Not Found")){
//            info = new String[1];
//            info[0] = "Not Found";
//        } else {
//            info = new String[10];
//            try {
                //Gets the Data from the JSON object from the API
//                JSONObject json = new JSONObject(data);
//                JSONObject location = json.getJSONObject("location");
//                String name = location.getString("name");
//                String region = location.getString("region");
//                info[0] = getString(R.string.detailCity) +" "+  name;
//                info[1] = getString(R.string.detailRegion) +" "+ region;
//                JSONObject current = json.getJSONObject("current");
//                double tempC = (current.getDouble("temp_c"));
//                double tempF = (current.getDouble("temp_f"));
//                info[2] = getString(R.string.detailTempC) +" "+ tempC;
//                info[3] = getString(R.string.detailTempF) +" "+ tempF;
//                int day = (current.getInt("is_day"));
//                //Checked to see if its day or night out.
//                if (day == 1) {
//                    info[4] = getString(R.string.detailDay);
//                } else {
//                    info[4] = getString(R.string.detailNight);
//                }
//                JSONObject condition = current.getJSONObject("condition");
//                String sky = condition.getString("text");
//                int windMPH = (current.getInt("wind_mph"));
//                int humidity = (current.getInt("humidity"));
//                double feelLikeC = (current.getDouble("feelslike_c"));
//                double feelLikeF = (current.getDouble("feelslike_f"));
//                info[5] = getString(R.string.detailSky) +" "+ sky;
//                info[6] = getString(R.string.detailWind) +" "+ windMPH;
//                info[7] = getString(R.string.detailHum) +" "+ humidity;
//                info[8] = getString(R.string.detailFeelC) +" "+ feelLikeC;
//                info[9] = getString(R.string.detailFeelF) +" "+ feelLikeF;
//                AppExecutors.getInstance().diskIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        mDb.weatherDao().insertCity(new City(name,region, tempC, tempF,sky, windMPH, humidity, feelLikeC, feelLikeF));
//                    }
//                });
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
    }

}