package com.example.weatherappsimple;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The data object for the Weather database that stores information about the weather in different cities.
 */
@Entity(tableName = "weather")
public class City {

    //The information we hold for each city.
    @NonNull
    @PrimaryKey
    String name;
    double tempF;
    double tempC;
    double feelLikeF;
    double feelLikeC;
    String region;
    int windMPH;
    int humidity;
    String sky;


    /**
     * Constructor
     * @param name      Name of the city
     * @param region    Region the city is in
     * @param tempC     Temperature in Celsius
     * @param tempF     Temperature in Fahrenheit
     * @param sky       String of how the sky is looking
     * @param windMPH   The speed of wind in MPH
     * @param humidity  The percentage of humidity
     * @param feelLikeC The temperature of how it feels in Celsius
     * @param feelLikeF The temperature of how it feels in Fahrenheit
     */
    public City(@NonNull String name, String region, double tempC, double tempF, String sky, int windMPH, int humidity, double feelLikeC, double feelLikeF){
        this.name = name;
        this.region = region;
        this.tempF = tempF;
        this.tempC = tempC;
        this.sky = sky;
        this.windMPH = windMPH;
        this.humidity = humidity;
        this.feelLikeF = feelLikeF;
        this.feelLikeC = feelLikeC;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public double getFeelLikeF() {
        return feelLikeF;
    }

    public void setFeelLikeF(double feelLikeF) {
        this.feelLikeF = feelLikeF;
    }

    public double getFeelLikeC() {
        return feelLikeC;
    }

    public void setFeelLikeC(double feelLikeC) {
        this.feelLikeC = feelLikeC;
    }

    /**
     * Gets a string array from the city object
     * Used for the view adapter (When looking at new city weather)
     *
     * @return A string[] of all the attributes of the city
     */
    public String[] getArray(){
        String[] ret = new String[9];
        ret[0] = "Name: " + name;
        ret[1] = "Region: " + region;
        ret[2] = "Temp(C): " + tempC;
        ret[3] = "Temp(F): " + tempF;
        ret[4] = "Sky: " + sky;
        ret[5] = "Wind(MPH): " + windMPH;
        ret[6] = "HumidityL " + humidity;
        ret[7] = "Feels Like(C): " + feelLikeC;
        ret[8] = "Feels Like(F): " + feelLikeF;
        return ret;
    }
}
