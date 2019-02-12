package com.example.luispena.earthquakeviewer.Room;

import android.arch.persistence.room.TypeConverter;
import android.location.Location;

import java.util.Date;

public class EarthquakeTypeConverter {

    @TypeConverter
    public static Date dateFromTimeStamp(Long value){

        return value == null ? null:new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){

        return date == null ? null :date.getTime();

    }

    @TypeConverter
    public static String locationToString(Location location){
        return location == null ? null : location.getLatitude() + "," + location.getLongitude();
    }

    @TypeConverter
    public static Location locationFromString(String location){

        if(location != null && (location.contains(","))){
            Location result = new Location("Generated");
            String[] locationsStrings = location.split(",");

            if (locationsStrings.length == 2){
                result.setLatitude(Double.parseDouble(locationsStrings[0]));
                result.setLongitude(Double.parseDouble(locationsStrings[1]));
                return result;
            } else{return null; }

        } else{ return null; }
    }
}
