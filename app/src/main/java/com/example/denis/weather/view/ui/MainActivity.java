package com.example.denis.weather.view.ui;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.denis.weather.R;
import com.example.denis.weather.view.fragments.MainFragment;
import com.example.denis.weather.viewModels.MainFragmentVM;

public class MainActivity extends AppCompatActivity implements MainFragmentVM.OnButtonRefreshClicked {




    public static final String TAG = "MainActivity";

    MainFragment mainFragment = MainFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place, mainFragment)
                .commit();
      //  updateViewPager = (UpdateViewPager) mainFragment.getActivity();

//

        //


    }


    @Override
    public void onButtonRefreshClick() {
        Log.i(TAG, "onButtonRefreshClick: main activity get click");
                MainFragmentVM test = new MainFragmentVM(new MainFragment());
        test.updateWeather(48.5, 35.0, this);



    }


//    @Override
//    public void onButtonRefreshClick() {
//        Log.i(TAG, "onButtonRefreshClick: main activity get click");
////
//    }


}
