package com.example.denis.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.denis.weather.fragments.MainFragment;
import com.example.denis.weather.viewModels.MainFragmentVM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place,MainFragment.newInstance())
                .commit();

        MainFragmentVM test = new MainFragmentVM(new MainFragment());
        test.updateWeather(48.5,35.0);

    }
}
