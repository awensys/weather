package com.example.denis.weather.viewModels;

import android.content.Context;
import android.util.Log;

import com.example.denis.weather.MainActivity;
import com.example.denis.weather.fragments.MainFragment;
import com.example.denis.weather.weather.Weather;
import com.example.denis.weather.weather.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
   // boolean check = isFilePresent(getActivity(), "storage.json");
    String FILENAME = "storage.json";

    public MainFragmentVM(MainFragment fragment) {
        super(fragment);
    }


    public void updateWeather(double latitude, double longitude) {

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

               MainActivity m = new MainActivity();

               m.writeToFile(response.body().toString());
            //   m.create(FILENAME,response.body().toString());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }



//    public JSONObject getJson() {
//
//     if(check) {
//        String jsonString = read(getActivity(), "storage.json");
//        //do the json parsing here and do the rest of functionality of app
//    } else {
//        boolean isFileCreated = create(getActivity(), "storage.json", "{}");
//        if (isFileCreated) {
//            //proceed with storing the first todo  or show ui
//        } else {
//            //show error or try again.
//        }
//    }
//return null;}

}