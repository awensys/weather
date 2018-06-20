package com.example.denis.weather.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.denis.weather.R;
import com.example.denis.weather.support.WeatherIcon;


public class NowWeatherFragment extends Fragment {

    private static final String MAIN_ICON = "main icon";
    private static final String ARG_PARAM2 = "param2";

    private WeatherIcon weatherIcon;


    public NowWeatherFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NowWeatherFragment newInstance(WeatherIcon icon) {
        NowWeatherFragment fragment = new NowWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(MAIN_ICON, icon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          weatherIcon = getArguments().getParcelable(MAIN_ICON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_weather, container, false);
        ImageView imageViewIcon = view.findViewById(R.id.icon_place);
        ImageView imageViewDivider = view.findViewById(R.id.divider_image_view);
        imageViewIcon.setImageResource(weatherIcon.getId());
        imageViewDivider.setImageResource(R.drawable.divider_line);


        return view;
    }

}
