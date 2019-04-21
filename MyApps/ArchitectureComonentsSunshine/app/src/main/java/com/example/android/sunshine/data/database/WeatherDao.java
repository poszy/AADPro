package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

@Dao
public interface WeatherDao {

    // when sunshine re-downloads forecasts, old weather forecast are replaced by new ones. this will
    // stop the app from crashing if there already are weather entries.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weather);

    @Query("SELECT * FROM weather WHERE date = :date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);

    @Query("DELETE FROM weather WHERE date < :date")
    void deleteOldWeather(Date date);

    @Query("SELECT COUNT(id) FROM weather WHERE date >= :date")
    int countAllFutureWeather(Date date);


}
