package com.example.denis.weather.view.ui;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.denis.weather.R;
import com.example.denis.weather.model.support.Geocode;
import com.example.denis.weather.model.support.SaveLoadPreferences;
import com.example.denis.weather.view.fragments.GoogleMapFragment;
import com.example.denis.weather.view.fragments.MainFragment;
import com.example.denis.weather.viewModels.MainFragmentVM;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements MainFragmentVM.OnButtonRefreshClicked, GoogleMapFragment.OnFinishMapFragment {




    public static final String TAG = "MainActivity";

    MainFragment mainFragment = MainFragment.newInstance();
    GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place, mainFragment)
                .commit();

    }


    @Override
    public void onButtonRefreshClick() {

//                MainFragmentVM test = new MainFragmentVM(new MainFragment());
//        test.updateWeather(48.5, 35.0, this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place,googleMapFragment)
                .addToBackStack("1")
                .commit();


    }

    @Override
    public void onFinish() {

    getSupportFragmentManager()
            .popBackStack();

    double lt = Double.parseDouble(SaveLoadPreferences.loadText(this,GoogleMapFragment.Lt));
    double ln = Double.parseDouble(SaveLoadPreferences.loadText(this,GoogleMapFragment.Ln));


        MainFragmentVM test = new MainFragmentVM(new MainFragment());
        test.updateWeather(lt, ln, this);

        Log.i(TAG, "onFinish: "+ Geocode.getAddress(this,lt,ln));


    }
}
