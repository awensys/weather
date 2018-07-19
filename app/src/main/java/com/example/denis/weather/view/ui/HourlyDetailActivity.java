package com.example.denis.weather.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.weather.R;
import com.example.denis.weather.model.singletons.IconSingleton;
import com.example.denis.weather.model.support.Geocode;
import com.example.denis.weather.model.support.SaveLoadPreferences;
import com.example.denis.weather.model.support.WeatherIcon;
import com.example.denis.weather.view.fragments.GoogleMapFragment;
import com.example.denis.weather.view.fragments.HourlyWeatherFragment;
import com.example.denis.weather.view.fragments.NowWeatherFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HourlyDetailActivity extends AppCompatActivity {

    ImageView icon;
    TextView summary, date, location, temperature, humidity, pressure, windSpeed, uvIndex;
    public ArrayList<WeatherIcon> icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_detail);

        icon = findViewById(R.id.hourly_detail_icon_iv);
        summary = findViewById(R.id.hourly_detail_summary_tv);
        date = findViewById(R.id.hourly_detail_date_tv);
        location = findViewById(R.id.hourly_detail_location_tv);
        temperature = findViewById(R.id.hourly_detail_temperature_tv);
        humidity = findViewById(R.id.hourly_detail_humidity_tv);
        pressure = findViewById(R.id.hourly_detail_pressure_tv);
        windSpeed = findViewById(R.id.hourly_detail_wind_speed_tv);
        uvIndex = findViewById(R.id.hourly_detail_uv_tv);
        icons = IconSingleton.getInstance().getIcons();

        for (int i = 0; i < icons.size(); i++) {
            if (icons.get(i).getName().equals(getIntent().getStringExtra(HourlyWeatherFragment.ICON))) {
                icon.setImageResource(icons.get(i).getId());
            }
        }

        summary.setText(getIntent().getStringExtra(HourlyWeatherFragment.SUMMARY));

        long unixSeconds = getIntent().getIntExtra(HourlyWeatherFragment.DATE,0);
        Date temp = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy   HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        String formattedDate = sdf.format(temp);
        date.setText(formattedDate);

        location.setText(getIntent().getStringExtra(HourlyWeatherFragment.LOCATION));

        temperature.setText(getIntent().getDoubleExtra(HourlyWeatherFragment.TEMPERATURE,20.0)+" °C");
        humidity.setText(getIntent().getDoubleExtra(HourlyWeatherFragment.HUMIDITY,20)+" %");
        pressure.setText(((int) (getIntent().getDoubleExtra(HourlyWeatherFragment.PRESSURE,20)*0.75))+" mm Hg");
        humidity.setText(getIntent().getDoubleExtra(HourlyWeatherFragment.WIND_SPEED,20)+" m/s");
        uvIndex.setText(getIntent().getIntExtra(HourlyWeatherFragment.UV,20)+"");



    }
}
