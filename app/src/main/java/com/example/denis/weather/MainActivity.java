package com.example.denis.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.denis.weather.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_place,MainFragment.newInstance())
                .commit();

    }
}
