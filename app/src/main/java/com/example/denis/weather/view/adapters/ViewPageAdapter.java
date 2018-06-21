package com.example.denis.weather.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.denis.weather.model.support.WriteObjectFile;
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.view.fragments.DailyWeatherFragment;
import com.example.denis.weather.view.fragments.HourlyWeatherFragment;
import com.example.denis.weather.view.fragments.NowWeatherFragment;
import com.example.denis.weather.model.singletons.IconSingleton;
import com.example.denis.weather.viewModels.MainFragmentVM;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ViewPageAdapter extends FragmentPagerAdapter {


    public static final String TAG = "TAG";
    Weather weather;
    WriteObjectFile writeObjectFile;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
        String mJsonString = WriteObjectFile.readObject(MainFragmentVM.FILENAME, container.getContext()).toString();
        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(mJsonString);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        weather = gson.fromJson(mJson, Weather.class);
        Log.i(TAG, "finishUpdate: "+weather.getTimezone());
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            NowWeatherFragment fragment = NowWeatherFragment.newInstance(weather);

            return fragment;
        } else if (position == 1) {
            return new HourlyWeatherFragment();
        } else if (position == 2) {
            return new DailyWeatherFragment();
        } else return new NowWeatherFragment();

    }

    @Override
    public int getCount() {
        return 3;
    }
}
