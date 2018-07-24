package com.example.denis.weather.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.weather.R;
import com.example.denis.weather.model.singletons.IconSingleton;
import com.example.denis.weather.model.support.WeatherIcon;
import com.example.denis.weather.view.fragments.DailyWeatherFragment;
import com.example.denis.weather.view.fragments.HourlyWeatherFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DailyDetailActivity extends AppCompatActivity {

    ImageView icon;
    TextView summary, date, location, temperature, humidity, pressure, windSpeed, sunrise, sunset;
    public ArrayList<WeatherIcon> icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_detail);


        icon = findViewById(R.id.daily_detail_icon_iv);
        summary = findViewById(R.id.daily_detail_summary_tv);
        date = findViewById(R.id.daily_detail_date_tv);
        location = findViewById(R.id.daily_detail_location_tv);
        temperature = findViewById(R.id.daily_detail_temperature_tv);
        humidity = findViewById(R.id.daily_detail_humidity_tv);
        pressure = findViewById(R.id.daily_detail_pressure_tv);
        windSpeed = findViewById(R.id.daily_detail_wind_speed_tv);
        sunrise = findViewById(R.id.daily_detail_sunrise_tv);
        sunset = findViewById(R.id.daily_detail_sunset_tv);
        icons = IconSingleton.getInstance().getIcons();

        for (int i = 0; i < icons.size(); i++) {
            if (icons.get(i).getName().equals(getIntent().getStringExtra(HourlyWeatherFragment.ICON))) {
                icon.setImageResource(icons.get(i).getId());
            }
        }

        summary.setText(getIntent().getStringExtra(HourlyWeatherFragment.SUMMARY));


        long date_value = getIntent().getIntExtra(DailyWeatherFragment.DATE, 0);
        long sunrise_value = getIntent().getIntExtra(DailyWeatherFragment.SUNRISE, 0);
        long sunset_value = getIntent().getIntExtra(DailyWeatherFragment.SUNSET, 0);
        Date date_date = new java.util.Date(date_value * 1000L);
        Date sunrise_date = new java.util.Date(sunrise_value * 1000L);
        Date sunset_date = new java.util.Date(sunset_value * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf_sunrise = new java.text.SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf_sunset = new java.text.SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        sdf_sunrise.setTimeZone(java.util.TimeZone.getDefault());
        sdf_sunset.setTimeZone(java.util.TimeZone.getDefault());
        String formattedDate = sdf.format(date_date);
        String formattedSunrise = sdf_sunrise.format(sunrise_date);
        String formattedSunset = sdf_sunset.format(sunset_date);

        date.setText(formattedDate);
        sunrise.setText(formattedSunrise);
        sunset.setText(formattedSunset);

        location.setText(getIntent().getStringExtra(HourlyWeatherFragment.LOCATION));

        temperature.setText(getIntent().getDoubleExtra(DailyWeatherFragment.TEMPERATURE_MIN, 20.0) +" ... "+getIntent().getDoubleExtra(DailyWeatherFragment.TEMPERATURE_MAX, 20.0) +" °C");
        humidity.setText(((int)(getIntent().getDoubleExtra(DailyWeatherFragment.HUMIDITY, 20)*100)) + " %");
        pressure.setText(((int) (getIntent().getDoubleExtra(DailyWeatherFragment.PRESSURE, 20) * 0.75)) + " mm Hg");
        windSpeed.setText(getIntent().getDoubleExtra(DailyWeatherFragment.WIND_SPEED,20)+" m/s");


    }
}
