package com.example.denis.weather.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denis.weather.R;
import com.example.denis.weather.model.support.Geocode;
import com.example.denis.weather.model.support.SaveLoadPreferences;
import com.example.denis.weather.model.weather.Daily;
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.view.adapters.RecyclerDailyViewAdapter;
import com.example.denis.weather.view.adapters.RecyclerHourlyViewAdapter;
import com.example.denis.weather.view.ui.DailyDetailActivity;
import com.example.denis.weather.view.ui.HourlyDetailActivity;


public class DailyWeatherFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    public static final String ICON="ICON";
    public static final String SUMMARY="SUMMARY";
    public static final String TEMPERATURE_MIN="TEMPERATURE_MIN";
    public static final String TEMPERATURE_MAX="TEMPERATURE_MAX";
    public static final String HUMIDITY="HUMIDITY";
    public static final String PRESSURE="PRESSURE";
    public static final String WIND_SPEED="WIND_SPEED";
    public static final String DATE="DATE";
    public static final String LOCATION="LOCATION";
    public static final String SUNRISE="SUNRISE";
    public static final String SUNSET="SUNSET";

    private Weather weather;
    RecyclerView recyclerView;
    RecyclerDailyViewAdapter adapter;
    static DailyWeatherFragment fragment;


    public DailyWeatherFragment() {
        // Required empty public constructor
    }

    public static DailyWeatherFragment getInstance() {
        return fragment;
    }

    public static DailyWeatherFragment newInstance(Weather weather) {
        fragment = new DailyWeatherFragment();
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
        return inflater.inflate(R.layout.fragment_daily_weather, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new RecyclerDailyViewAdapter(weather);
        recyclerView = view.findViewById(R.id.recycler_view_daily);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        try {
            if (weather != null) {
                int count = recyclerView.getChildAdapterPosition(view);
                Intent intent = new Intent(getActivity(), DailyDetailActivity.class);
                intent.putExtra(ICON, weather.getDaily().getData().get(count).getIcon());
                intent.putExtra(SUMMARY, weather.getDaily().getData().get(count).getSummary());
                intent.putExtra(TEMPERATURE_MIN, weather.getDaily().getData().get(count).getTemperatureMin());
                intent.putExtra(TEMPERATURE_MAX, weather.getDaily().getData().get(count).getTemperatureMax());
                intent.putExtra(HUMIDITY, weather.getDaily().getData().get(count).getHumidity());
                intent.putExtra(PRESSURE, weather.getDaily().getData().get(count).getPressure());
                intent.putExtra(WIND_SPEED, weather.getDaily().getData().get(count).getWindSpeed());
                intent.putExtra(DATE, weather.getDaily().getData().get(count).getTime());
                intent.putExtra(SUNRISE, weather.getDaily().getData().get(count).getSunriseTime());
                intent.putExtra(SUNSET, weather.getDaily().getData().get(count).getSunsetTime());
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
