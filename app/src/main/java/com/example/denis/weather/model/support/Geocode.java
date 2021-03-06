package com.example.denis.weather.model.support;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Geocode {

    public static final String TAG = "GEOCODE";

    public static String getAddress(Context context, double lt, double ln) {
        String add = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lt, ln, 1);
            Address obj = addresses.get(0);

//            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getAdminArea();


            Log.i(TAG, "getAddress: " + obj.getAdminArea());

        } catch (IOException e) {

            e.printStackTrace();

        }
        return add;
    }

}
