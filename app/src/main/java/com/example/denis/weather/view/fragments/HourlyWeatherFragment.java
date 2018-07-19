package com.example.denis.weather.view.fragments;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denis.weather.R;
import com.example.denis.weather.model.support.Geocode;
import com.example.denis.weather.model.support.SaveLoadPreferences;
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.view.adapters.RecyclerHourlyViewAdapter;
import com.example.denis.weather.view.ui.HourlyDetailActivity;
import com.example.denis.weather.view.ui.MainActivity;


public class HourlyWeatherFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = "hourlyWeatherFragment";
    public static final String ICON = "ICON";
    public static final String SUMMARY = "SUMMARY";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String HUMIDITY = "HUMIDITY";
    public static final String PRESSURE = "PRESSURE";
    public static final String WIND_SPEED = "WIND_SPEED";
    public static final String UV = "UV";
    public static final String DATE = "DATE";
    public static final String LOCATION = "LOCATION";
    Weather weather;

    RecyclerView recyclerView;
    RecyclerHourlyViewAdapter adapter;
    static HourlyWeatherFragment fragment;

    public HourlyWeatherFragment() {
        // Required empty public constructor
    }

    public static HourlyWeatherFragment getInstance() {
        return fragment;
    }

    public static HourlyWeatherFragment newInstance(Weather weather) {
        fragment = new HourlyWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, weather);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weather = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hourly_weather, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        recyclerView = view.findViewById(R.id.recycler_view_hourly);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerHourlyViewAdapter(weather);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View view) {
        try {
            if (weather != null) {
                int count = recyclerView.getChildAdapterPosition(view);
                Intent intent = new Intent(getActivity(), HourlyDetailActivity.class);
                intent.putExtra(ICON, weather.getHourly().getData().get(count).getIcon());
                intent.putExtra(SUMMARY, weather.getHourly().getData().get(count).getSummary());
                intent.putExtra(TEMPERATURE, weather.getHourly().getData().get(count).getTemperature());
                intent.putExtra(HUMIDITY, weather.getHourly().getData().get(count).getHumidity());
                intent.putExtra(PRESSURE, weather.getHourly().getData().get(count).getPressure());
                intent.putExtra(WIND_SPEED, weather.getHourly().getData().get(count).getWindSpeed());
                intent.putExtra(UV, weather.getHourly().getData().get(count).getUvIndex());
                intent.putExtra(DATE, weather.getHourly().getData().get(count).getTime());
                double lt = Double.parseDouble(SaveLoadPreferences.loadText(getActivity(), GoogleMapFragment.LATITUDE));
                double ln = Double.parseDouble(SaveLoadPreferences.loadText(getActivity(), GoogleMapFragment.LONGITUDE));
                intent.putExtra(LOCATION, Geocode.getAddress(getContext(), lt, ln));

                startActivity(intent);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}