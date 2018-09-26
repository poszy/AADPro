package com.luispena.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    // in this case every word is a primary key
    //

    // each field in the database needs to have
    // its own public "getter" method
    public Word(String word){this.mWord = word; }
    public String getWord(){return this.mWord;}


}

