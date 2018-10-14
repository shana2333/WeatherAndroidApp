package com.example.shana.gsonweather;

import com.google.gson.annotations.SerializedName;

public class WeatherForecast {

    @SerializedName("time")
    long time;

    @SerializedName("summary")
    String summary;

    @SerializedName("icon")
    String icon;

    @SerializedName("precipIntensity")
    float precipIntensity;

    @SerializedName("precipProbability")
    float precipProbability;

    @SerializedName("temperature")
    float temperature;

    @SerializedName("apparentTemperature")
    float apparentTemperature;

    @SerializedName("dewPoint")
    float dewPoint;

    @SerializedName("humidity")
    float humidity;

    @SerializedName("pressure")
    float pressure;

    @SerializedName("windSpeed")
    float windSpeed;

    @SerializedName("windGust")
    float windGust;

    @SerializedName("windBearing")
    float windBearing;

    @SerializedName("cloudCover")
    float cloudCover;

    @SerializedName("uvIndex")
    float uvIndex;

    @SerializedName("visibility")
    float visibility;

    @SerializedName("ozone")
    float ozone;

}
