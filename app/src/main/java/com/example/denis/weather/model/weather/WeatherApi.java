package com.example.denis.weather.model.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    @GET("{latitude},{longitude}?lang=uk&units=auto")
    Call<Weather> getWeather(@Path("latitude") double latitude, @Path("longitude") double longitude);

}
