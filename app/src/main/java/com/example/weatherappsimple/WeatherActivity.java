package com.example.weatherappsimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import org.json.JSONException;
import org.json.JSONObject;


public class WeatherActivity extends AppCompatActivity {
    private RecyclerView recView;

    /**
     * On create is called with this activity is started/resumed
     * @param savedInstanceState State of the application if saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        recView = findViewById(R.id.recyclerView);
        //Create Recycler View
        int gridCount = getResources().getInteger(R.integer.grid_column_count);
        recView.setLayoutManager(new GridLayoutManager(this, gridCount));
        //Get the extras from the previous activity (Data of the weather)
        Intent intent = getIntent();
        String data = intent.getStringExtra("Data");
        String[] info;
        //Checks to see if the city was found and says not found if it wasn't and sets the text to the data if it is.
        if(data.equals("Not Found")){
            info = new String[1];
            info[0] = "Not Found";
        } else {
            info = new String[10];
            try {
                //Gets the Data from the JSON object from the API
                JSONObject json = new JSONObject(data);
                JSONObject location = json.getJSONObject("location");
                info[0] = getString(R.string.detailCity) +" "+  location.getString("name");
                info[1] = getString(R.string.detailRegion) +" "+ location.getString("region");
                JSONObject current = json.getJSONObject("current");
                info[2] = getString(R.string.detailTempC) +" "+ (current.getInt("temp_c"));
                info[3] = getString(R.string.detailTempF) +" "+ (current.getInt("temp_f"));
                int day = (current.getInt("is_day"));
                //Checked to see if its day or night out.
                if (day == 1) {
                    info[4] = getString(R.string.detailDay);
                } else {
                    info[4] = getString(R.string.detailNight);
                }
                JSONObject condition = current.getJSONObject("condition");
                info[5] = getString(R.string.detailSky) +" "+ condition.getString("text");
                info[6] = getString(R.string.detailWind) +" "+ (current.getInt("wind_mph"));
                info[7] = getString(R.string.detailHum) +" "+ (current.getInt("humidity"));
                info[8] = getString(R.string.detailFeelC) +" "+ (current.getInt("feelslike_c"));
                info[9] = getString(R.string.detailFeelF) +" "+ (current.getInt("feelslike_f"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Puts the data into the view.
        ViewAdapter adapter = new ViewAdapter(info);
        recView.setAdapter(adapter);
    }

}