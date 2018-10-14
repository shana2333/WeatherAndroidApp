package com.example.shana.gsonweather;

import com.google.gson.annotations.SerializedName;

public class DarkSkyResponse {

    @SerializedName("latitude")
    float latitude;

    @SerializedName("longitude")
    float longitude;

    @SerializedName("timezone")
    String timezone;

    @SerializedName("offset")
    int offset;

    @SerializedName("currently")
    WeatherForecast currently;
}
