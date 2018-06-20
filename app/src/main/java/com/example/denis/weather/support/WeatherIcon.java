package com.example.denis.weather.support;

public class WeatherIcon {

    int id;
    String name;

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
}
