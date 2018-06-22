package com.example.denis.weather.view.adapters;

import android.support.annotation.NonNull;
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
import com.example.denis.weather.viewModels.MainFragmentVM;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ViewPageAdapter extends FragmentPagerAdapter {


    public static final String TAG = "TAG";
    Weather weather;


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            String mJsonString = WriteObjectFile.readObject(MainFragmentVM.FILENAME, container.getContext()).toString();

            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(mJsonString);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            weather = gson.fromJson(mJson, Weather.class);
            Log.i(TAG, "finishUpdate: " + weather.getTimezone());
        } catch (NullPointerException e) {

        }
        return super.instantiateItem(container, position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NowWeatherFragment.newInstance(weather);
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
