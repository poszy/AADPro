package com.example.luispena.earthquakeviewer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.location.Location;
import android.support.annotation.NonNull;

public class Earthquake {

    private String mID;
    private String mDetails;
    private String mLink;
    private Date mDate;
    private Location mLocation;
    private double mMagnitude;

    public String getmID() { return mID; }
    public Date getmDate() { return mDate; }
    public String getmDetails() { return mDetails; }
    public Location getmLocation() { return mLocation; }
    public double getmMagnitude() { return mMagnitude; }
    public String getmLink() { return mLink; }

    public Earthquake( String id, Date date, String details, Location location, double magnitude, String link){
        mID = id;
        mDate = date;
        mDetails = details;
        mLocation = location;
        mMagnitude = magnitude;
        mLink = link;

    }
    @NonNull
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm", Locale.US);
        String dateString = sdf.format(mDate);
        return dateString + ": " + mMagnitude + " " + mDetails;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Earthquake){
            return (((Earthquake)obj).getmID().contentEquals(mID));
        }else{
            return  false;
        }
    }


}
