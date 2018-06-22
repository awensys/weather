package com.example.denis.weather.viewModels;


import android.content.Context;
import android.util.Log;

import com.example.denis.weather.model.support.WriteObjectFile;
import com.example.denis.weather.view.fragments.MainFragment;
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.model.weather.WeatherApi;
import com.example.denis.weather.view.fragments.NowWeatherFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragmentVM extends FragmentViewModel<MainFragment> {


    public interface OnButtonRefreshClicked {
        void onButtonRefreshClick();
    }

    public interface OnUpdateViewPager {
        void onUpdateView();
    }

    public static final String TAG = "ViewModel";
    Gson gson;
    Retrofit weatherRetrofit;
    WeatherApi weatherApi;
    public static final String FILENAME = "storage.json";
    OnButtonRefreshClicked clicked;
    OnUpdateViewPager onUpdate;
    MainFragment fragment;

    public MainFragmentVM(MainFragment fragment) {
        super(fragment);
        clicked = (OnButtonRefreshClicked) getActivity();
        onUpdate = fragment;
        this.fragment = fragment;
    }


    public void updateWeather(double latitude, double longitude, final Context context) {


        gson = new GsonBuilder()
                .setLenient()
                .create();

        weatherRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.darksky.net/forecast/76909458ade0ca45f4162a35d5ff05cc/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        weatherApi = weatherRetrofit.create(WeatherApi.class);

        weatherApi.getWeather(latitude, longitude).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Log.i(TAG, "onResponse: " + response.body().getCurrently().getTemperature());
                Gson gson = new Gson();
                String json = gson.toJson(response.body());
                WriteObjectFile.writeObject(json, MainFragmentVM.FILENAME, context);
                onUpdate.onUpdateView();


            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public void onButtonRefreshClick() {
        Log.i(TAG, "onButtonRefreshClick: refresh clicked in VM");

        clicked.onButtonRefreshClick();

    }


}