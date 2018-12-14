package com.example.luispena.roomwordsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// There is only one entity (table)
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    // Define a DAO that will be used to access the database
    //All of the methods in an interface are implicitly abstract, so the abstract modifier is not used with interface methods (it could be—it's just not necessary).
    // https://softwareengineering.stackexchange.com/questions/111638/can-i-consider-interface-methods-as-abstract-methods
    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase( final Context context){

        if (INSTANCE == null) {
            // Only one thread can execute at a time. WordRoomDatabase.class whose lock associates with the monitor.
            // The code is said to be synchronized on this class
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating, if no Migration object. Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return  INSTANCE;
    }
}
