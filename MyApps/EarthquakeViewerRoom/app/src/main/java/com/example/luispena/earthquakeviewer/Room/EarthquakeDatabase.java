package com.example.luispena.earthquakeviewer.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.luispena.earthquakeviewer.Earthquake;

@Database(entities = {Earthquake.class}, version = 1)
@TypeConverters({EarthquakeTypeConverter.class})
public abstract class EarthquakeDatabase extends RoomDatabase {

    public abstract EarthquakeDAO earthquakeDAO();
}
