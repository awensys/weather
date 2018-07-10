package com.example.denis.weather.viewModels;

import android.util.Log;

import com.example.denis.weather.view.fragments.GoogleMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

public class GoogleMapFragmentVM extends FragmentViewModel<GoogleMapFragment> {

    public static final String TAG="GoogleMapFragmentVM";

    public GoogleMapFragmentVM(GoogleMapFragment fragment) {
        super(fragment);
    }

}