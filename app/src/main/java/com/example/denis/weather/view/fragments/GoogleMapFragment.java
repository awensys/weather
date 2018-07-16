package com.example.denis.weather.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.denis.weather.R;
import com.example.denis.weather.BR;
import com.example.denis.weather.model.support.Geocode;
import com.example.denis.weather.model.support.SaveLoadPreferences;
import com.example.denis.weather.viewModels.GoogleMapFragmentVM;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.example.denis.weather.databinding.FragmentGoogleMapBinding;


public class GoogleMapFragment extends BindingFragment<GoogleMapFragmentVM, FragmentGoogleMapBinding> implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    public static final String TAG = "GoogleMapFragment";

    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    public static final String LOCATION_NAME = "LocationName";

    GoogleMap gMap;
    SupportMapFragment mapFragment;
    OnFinishMapFragment onFinish;
    Context context;

    public interface OnFinishMapFragment {
        void onFinish();
    }

    public GoogleMapFragment() {
        // Required empty public constructor
    }

    public static GoogleMapFragment newInstance() {
        return new GoogleMapFragment();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        context = getContext();
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        super.onViewCreated(view, savedInstanceState);
        onFinish = (OnFinishMapFragment) context;
    }

    @Override
    protected GoogleMapFragmentVM onCreateViewModel(FragmentGoogleMapBinding binding) {
        return new GoogleMapFragmentVM(this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_google_map;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setOnMapClickListener(this);
        double lt = 48.5;
        double ln = 35;

        if (SaveLoadPreferences.loadText(getActivity(), LATITUDE)!=null) {
            lt = Double.parseDouble(SaveLoadPreferences.loadText(getActivity(), LATITUDE));
            ln = Double.parseDouble(SaveLoadPreferences.loadText(getActivity(), LONGITUDE));
        }
        LatLng pos = new LatLng(lt, ln);
        gMap.addMarker(new MarkerOptions().position(pos));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        SaveLoadPreferences.saveText(getActivity(), LATITUDE, String.valueOf(latLng.latitude));
        SaveLoadPreferences.saveText(getActivity(), LONGITUDE, String.valueOf(latLng.longitude));
        SaveLoadPreferences.saveText(getActivity(), LOCATION_NAME, Geocode.getAddress(getContext(),latLng.latitude,latLng.longitude));
        onFinish.onFinish();


    }
}


