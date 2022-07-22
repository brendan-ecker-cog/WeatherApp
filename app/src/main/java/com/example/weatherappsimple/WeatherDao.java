package com.example.weatherappsimple;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * The DAO for the Weather Database
 */
@Dao
public interface WeatherDao {

    // Query to get all entries
    @Query("SELECT * FROM WEATHER ORDER BY NAME")
    List<City> loadAllCity();

    // Insert a new city
    @Insert
    void insertCity(City city);

    // Update city (Could not get working)
    @Query("UPDATE WEATHER SET tempF = :tempF AND tempC = :tempC and sky = :sky and windMPH = :windMPH and humidity=:humidity and feelLikeC = :feelLikeC and feelLikeF = :feelLikeF")
    void updateCity(double tempF, double tempC, String sky, int windMPH, int humidity, double feelLikeC, double feelLikeF);

    //Delete a city
    @Delete
    void deleteCity(City city);

    // Get specific city with certain name
    @Query("SELECT * FROM WEATHER WHERE name = :name")
    City loadCityByName(String name);

}
