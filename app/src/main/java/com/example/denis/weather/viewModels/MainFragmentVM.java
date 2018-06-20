package com.example.denis.weather.viewModels;

import android.util.Log;

import com.example.denis.weather.fragments.MainFragment;
import com.example.denis.weather.weather.Weather;
import com.example.denis.weather.weather.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragmentVM extends FragmentViewModel<MainFragment> {

    public static final String TAG = "tag";
    Gson gson;
    Retrofit weatherRetrofit;
    WeatherApi weatherApi;

    public MainFragmentVM(MainFragment fragment) {
        super(fragment);
    }


    public void updateWeather(double lati, double longi) {

        gson = new GsonBuilder()
                .setLenient()
                .create();

        weatherRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.darksky.net/forecast/76909458ade0ca45f4162a35d5ff05cc/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        weatherApi = weatherRetrofit.create(WeatherApi.class);

        weatherApi.getWeather(lati, longi).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Log.i(TAG, "onResponse: " + response.body().getCurrently().getTemperature());

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

}