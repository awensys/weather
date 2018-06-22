package com.example.denis.weather.view.fragments;


import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.weather.R;
import com.example.denis.weather.model.singletons.IconSingleton;
import com.example.denis.weather.model.support.WeatherIcon;
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.viewModels.MainFragmentVM;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NowWeatherFragment extends Fragment  {

    private static final String WEATHER = "weather";
    public static final String TAG = "NowWeatherFragment";

    private Weather weather;
    public static ArrayList<WeatherIcon> icons;


    public NowWeatherFragment() {
        // Required empty public constructor
    }


    public static NowWeatherFragment newInstance(Weather weather) {
        icons = IconSingleton.getInstance().getIcons();
        NowWeatherFragment fragment = new NowWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(WEATHER, weather);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weather = getArguments().getParcelable(WEATHER);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_weather, container, false);

        ImageView imageViewIcon = view.findViewById(R.id.icon_place);
        ImageView imageViewDivider = view.findViewById(R.id.divider_image_view);
        TextView textViewTemperature = view.findViewById(R.id.temperature_text_view);
        TextView textViewSummary = view.findViewById(R.id.summary_text_view);
        TextView textViewUpdated = view.findViewById(R.id.updated_text_view);
        imageViewDivider.setImageResource(R.drawable.divider_line);
        if (weather != null) {
            for (int i = 0; i < icons.size(); i++) {
                if (icons.get(i).getName().equals(weather.getCurrently().getIcon())) {
                    imageViewIcon.setImageResource(icons.get(i).getId());
                }
            }
            textViewSummary.setText(weather.getCurrently().getSummary());

            textViewTemperature.setText(String.valueOf(weather.getCurrently().getTemperature().intValue()));

            long unixSeconds = weather.getCurrently().getTime();
            Date date = new java.util.Date(unixSeconds * 1000L);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getDefault());
            String formattedDate = sdf.format(date);
            textViewUpdated.setText(formattedDate);


        } else {
            imageViewIcon.setImageResource(icons.get(2).getId());
            textViewTemperature.setText("20");
            textViewSummary.setText("");
            textViewUpdated.setText("Please update data");
        }


        return view;
    }


}
