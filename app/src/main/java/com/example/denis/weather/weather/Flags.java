
package com.example.denis.weather.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flags implements Parcelable
{

    private List<String> sources = null;
    private List<String> isdStations = null;
    private String units;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Flags> CREATOR = new Creator<Flags>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Flags createFromParcel(Parcel in) {
            return new Flags(in);
        }

        public Flags[] newArray(int size) {
            return (new Flags[size]);
        }

    }
    ;

    protected Flags(Parcel in) {
        in.readList(this.sources, (String.class.getClassLoader()));
        in.readList(this.isdStations, (String.class.getClassLoader()));
        this.units = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Flags() {
    }

    /**
     * 
     * @param isdStations
     * @param units
     * @param sources
     */
    public Flags(List<String> sources, List<String> isdStations, String units) {
        super();
        this.sources = sources;
        this.isdStations = isdStations;
        this.units = units;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public List<String> getIsdStations() {
        return isdStations;
    }

    public void setIsdStations(List<String> isdStations) {
        this.isdStations = isdStations;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(sources);
        dest.writeList(isdStations);
        dest.writeValue(units);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "Flags{" +
                "sources=" + sources +
                ", isdStations=" + isdStations +
                ", units='" + units + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
