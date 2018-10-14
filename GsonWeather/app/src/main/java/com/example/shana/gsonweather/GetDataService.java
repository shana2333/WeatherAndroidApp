package com.example.shana.gsonweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("/forecast/2bb07c3bece89caf533ac9a5d23d8417/{latitude},{longitude}")
    Call<DarkSkyResponse> getForecastForLocation(@Path("latitude") String latitude, @Path("longitude") String longitude);
}
