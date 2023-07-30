package com.parvardegari.teamyarweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {

    @SerializedName("currentTemp")
    @Expose
    int currentTemp;

    @SerializedName("minimumTemp")
    @Expose
    int minimumTemp;

    @SerializedName("maximumTemp")
    @Expose
    int maximumTemp;

    public Temp(int currentTemp, int minimumTemp, int maximumTemp) {
        this.currentTemp = currentTemp;
        this.minimumTemp = minimumTemp;
        this.maximumTemp = maximumTemp;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(int minimumTemp) {
        this.minimumTemp = minimumTemp;
    }

    public int getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(int maximumTemp) {
        this.maximumTemp = maximumTemp;
    }
}
