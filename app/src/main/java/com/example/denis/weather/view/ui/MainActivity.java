package com.example.denis.weather.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.denis.weather.R;
import com.example.denis.weather.model.support.SaveLoadPreferences;
import com.example.denis.weather.view.fragments.GoogleMapFragment;
import com.example.denis.weather.view.fragments.MainFragment;
import com.example.denis.weather.viewModels.MainFragmentVM;

public class MainActivity extends AppCompatActivity implements MainFragmentVM.OnButtonRefreshClicked, GoogleMapFragment.OnFinishMapFragment, MenuItem.OnMenuItemClickListener {


    public static final String TAG = "MainActivity";

    MainFragment mainFragment = MainFragment.newInstance();
    GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place, mainFragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menu_location = menu.findItem(R.id.menu_location_button);
        MenuItem menu_map = menu.findItem(R.id.menu_map_button);
        MenuItem menu_settings = menu.findItem(R.id.menu_settings_button);
        MenuItem menu_info = menu.findItem(R.id.menu_info_button);



        return true;
    }

    @Override
    public void onButtonRefreshClick() {

//                MainFragmentVM test = new MainFragmentVM(new MainFragment());
//        test.updateWeather(48.5, 35.0, this);



    }

    @Override
    public void onFinish() {

        getSupportFragmentManager()
                .popBackStack();

        double lt = Double.parseDouble(SaveLoadPreferences.loadText(this, GoogleMapFragment.LATITUDE));
        double ln = Double.parseDouble(SaveLoadPreferences.loadText(this, GoogleMapFragment.LONGITUDE));


        MainFragmentVM test = new MainFragmentVM(new MainFragment());
        test.updateWeather(lt, ln, this);


    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Log.i(TAG, "onMenuItemClick: ");

        switch (menuItem.getItemId()) {
            case R.id.menu_map_button:

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_place, googleMapFragment)
                        .addToBackStack("1")
                        .commit();
                break;
            case R.id.menu_location_button:
                Log.i(TAG, "onMenuItemClick: ");
                break;
            case R.id.menu_settings_button:
                break;
            case R.id.menu_info_button:
                break;
            default:
                break;
        }

        return true;
    }
}
