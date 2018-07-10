package com.example.denis.weather.model.support;

import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SaveLoadPreferences {

    static SharedPreferences sPref;

    public static void saveText(Activity activity, String name, String value) {
        sPref = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(name, value);
        ed.commit();

    }

    public static String loadText(Activity activity, String name) {
        String value="";
        try {
            sPref = activity.getPreferences(MODE_PRIVATE);
         value   = sPref.getString(name, null);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return value;
    }
}
