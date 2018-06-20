package com.example.denis.weather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.denis.weather.fragments.DailyWeatherFragment;
import com.example.denis.weather.fragments.HourlyWeatherFragment;
import com.example.denis.weather.fragments.NowWeatherFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NowWeatherFragment();
        } else if(position==1){
            return  new HourlyWeatherFragment();
        }else if(position==2){
            return new DailyWeatherFragment();
        }else return new NowWeatherFragment();

    }

    @Override
    public int getCount() {
        return 3;
    }
}
