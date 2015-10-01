package com.example.designsupportlibraryuseing.otto;

/**
 * Created by 晋伟 on 2015/9/22 0022.
 */
public class LocationChangedEvent {
    public final int lat;
    public final int lon;

    public LocationChangedEvent(int lat, int lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return new StringBuilder("(") //
                .append(lat) //
                .append(", ") //
                .append(lon) //
                .append(")") //
                .toString();
    }
}
