
package com.example.denis.weather.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Weather implements Parcelable
{

    private Double latitude;
    private Double longitude;
    private String timezone;
    private Currently currently;
    private Hourly hourly;
    private Daily daily;
    private Flags flags;
    private Integer offset;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Weather> CREATOR = new Creator<Weather>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return (new Weather[size]);
        }

    }
    ;

    protected Weather(Parcel in) {
        this.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
        this.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
        this.currently = ((Currently) in.readValue((Currently.class.getClassLoader())));
        this.hourly = ((Hourly) in.readValue((Hourly.class.getClassLoader())));
        this.daily = ((Daily) in.readValue((Daily.class.getClassLoader())));
        this.flags = ((Flags) in.readValue((Flags.class.getClassLoader())));
        this.offset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Weather() {
    }

    /**
     * 
     * @param timezone
     * @param flags
     * @param currently
     * @param longitude
     * @param latitude
     * @param offset
     * @param hourly
     * @param daily
     */
    public Weather(Double latitude, Double longitude, String timezone, Currently currently, Hourly hourly, Daily daily, Flags flags, Integer offset) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currently = currently;
        this.hourly = hourly;
        this.daily = daily;
        this.flags = flags;
        this.offset = offset;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(timezone);
        dest.writeValue(currently);
        dest.writeValue(hourly);
        dest.writeValue(daily);
        dest.writeValue(flags);
        dest.writeValue(offset);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", currently=" + currently +
                ", hourly=" + hourly +
                ", daily=" + daily +
                ", flags=" + flags +
                ", offset=" + offset +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
