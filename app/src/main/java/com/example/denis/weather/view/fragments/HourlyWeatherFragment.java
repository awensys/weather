package com.example.denis.weather.view.fragments;

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
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.view.adapters.RecyclerHourlyViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HourlyWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HourlyWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HourlyWeatherFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    Weather weather;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    RecyclerHourlyViewAdapter adapter;

    public HourlyWeatherFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HourlyWeatherFragment newInstance(Weather weather) {
        HourlyWeatherFragment fragment = new HourlyWeatherFragment();
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

        adapter = new RecyclerHourlyViewAdapter(weather);
        recyclerView = view.findViewById(R.id.recycler_view_hourly);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();

        super.onViewCreated(view, savedInstanceState);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
