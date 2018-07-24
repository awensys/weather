package com.example.denis.weather.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.denis.weather.R;

public class InfoActivity extends AppCompatActivity {

    TextView info_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        info_tv = findViewById(R.id.info_tv);

        info_tv.setText("weather api: https://api.darksky.net" +
                    " \nicons:            https://icons8.ru");
    }
}
