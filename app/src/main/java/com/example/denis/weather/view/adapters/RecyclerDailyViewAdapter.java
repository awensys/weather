package com.example.denis.weather.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.weather.R;
import com.example.denis.weather.model.singletons.IconSingleton;
import com.example.denis.weather.model.support.WeatherIcon;
import com.example.denis.weather.model.weather.Weather;
import com.example.denis.weather.view.fragments.DailyWeatherFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RecyclerDailyViewAdapter extends RecyclerView.Adapter<RecyclerDailyViewAdapter.ViewHolder> {

    public static final String TAG = "RecyclerDailyView";
    Weather weather;
    ArrayList<WeatherIcon> icons;

    public RecyclerDailyViewAdapter(Weather weather) {
        this.weather = weather;
        icons = IconSingleton.getInstance().getIcons();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
viewItem.setOnClickListener(DailyWeatherFragment.getInstance());
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            for (int i = 0; i < icons.size(); i++) {
                if (icons.get(i).getName().equals(weather.getDaily().getData().get(position).getIcon())) {
                    holder.iconImageView.setImageResource(icons.get(i).getId());
                }
            }
            long unixSeconds = weather.getDaily().getData().get(position).getTime();
            Date date = new Date(unixSeconds * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(java.util.TimeZone.getDefault());
            String formattedDate = sdf.format(date);
            holder.updateTextView.setText(formattedDate);
            holder.temperatureTextView.setText(String.valueOf(weather.getDaily().getData().get(position).getTemperatureHigh().intValue()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return weather.getDaily().getData().size();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView updateTextView;
        TextView temperatureTextView;
        ImageView iconImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            updateTextView = itemView.findViewById(R.id.updated_text_view_for_list);
            temperatureTextView = itemView.findViewById(R.id.temperature_text_view_for_list);
            iconImageView = itemView.findViewById(R.id.icon_image_view_for_list);
        }
    }
}
