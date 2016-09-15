package com.example.android.quakereport;

/**
 * Created by abakshi on 9/14/2016.
 */
public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTime;
    private String mUrl;

    public Earthquake(double mag, String city, long time, String url) {
        mMagnitude = mag;
        mLocation = city;
        mTime = time;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTime() {
        return mTime;
    }

    public String getUrl() {
        return mUrl;
    }
}
