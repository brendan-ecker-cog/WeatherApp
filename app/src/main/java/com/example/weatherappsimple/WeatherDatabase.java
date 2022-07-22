package com.example.weatherappsimple;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    private static String TAG  = WeatherDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "weather";
    private static final Object LOCK = new Object();
    private static WeatherDatabase sInstance;

    public static WeatherDatabase getInstance(Context context){
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                WeatherDatabase.class, WeatherDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract WeatherDao weatherDao();
}
