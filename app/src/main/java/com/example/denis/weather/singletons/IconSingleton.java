package com.example.denis.weather.singletons;

import com.example.denis.weather.R;
import com.example.denis.weather.support.WeatherIcon;

import java.util.ArrayList;
import java.util.List;

public class IconSingleton {
    private static final IconSingleton ourInstance = new IconSingleton();

    List<WeatherIcon> icons = new ArrayList<>();

    public List<WeatherIcon> getIcons() {
        return icons;
    }

  public static IconSingleton getInstance() {
        return ourInstance;
    }

    private IconSingleton() {

   icons.add(new WeatherIcon(R.drawable.fog,"fog"));
   icons.add(new WeatherIcon(R.drawable.hail,"hail"));
   icons.add(new WeatherIcon(R.drawable.clear_day,"clear-day"));
   icons.add(new WeatherIcon(R.drawable.clear_night,"clear-night"));
   icons.add(new WeatherIcon(R.drawable.partly_cloudy_day,"partly-cloudy-day"));
   icons.add(new WeatherIcon(R.drawable.partly_cloudy_night,"partly-cloudy-night"));
   icons.add(new WeatherIcon(R.drawable.cloudy,"cloudy"));
   icons.add(new WeatherIcon(R.drawable.rain,"rain"));
   icons.add(new WeatherIcon(R.drawable.sleet,"sleet"));
   icons.add(new WeatherIcon(R.drawable.snow,"snow"));
   icons.add(new WeatherIcon(R.drawable.thunderstorm,"thunderstorm"));
   icons.add(new WeatherIcon(R.drawable.tornado,"tornado"));
   icons.add(new WeatherIcon(R.drawable.wind,"wind"));

    }
}
