package com.example.denis.weather.support;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherIcon implements Parcelable {

    int id;
    String name;

    protected WeatherIcon(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<WeatherIcon> CREATOR = new Creator<WeatherIcon>() {
        @Override
        public WeatherIcon createFromParcel(Parcel in) {
            return new WeatherIcon(in);
        }

        @Override
        public WeatherIcon[] newArray(int size) {
            return new WeatherIcon[size];
        }
    };

    @Override
    public String toString() {
        return "WeatherIcon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public WeatherIcon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
