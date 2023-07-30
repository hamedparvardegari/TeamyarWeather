package com.parvardegari.teamyarweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("cityName")
    @Expose
    String cityName;

    @SerializedName("longitude")
    @Expose
    double longitude;

    @SerializedName("latitude")
    @Expose
    double latitude;


    public City(int id, String cityName, double longitude, double latitude) {
        this.id = id;
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
