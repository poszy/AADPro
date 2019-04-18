package com.example.android.sunshine.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities =  {WeatherEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class WeatherDatabase extends RoomDatabase {
    private static WeatherDatabase instance;
    public abstract WeatherDao weatherDao();

    public static synchronized  WeatherDatabase getInstance(Context context){

        // Template pulled from github.com/poszy/AADPro/blob/master/MyApps/ArchExample/app/src/main/java/luispena/dev/archexample/NoteDatabase.java
        // synchronized in this case will make sure only one thread at a time can access the database and perform operations on
        // it accordingly. this is so data does not mess up and remains persistant
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "weather_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }

}
