package com.luispena.roomwordsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    // In the DAO (data access object), you specify SQL queries and associate them with method call
    // DAO must be an interface or abstract class
    // by default, all queries must be executed on a separate thread


    // This will insert a new word without having to write sql code
    @Insert
    void insert( Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}
