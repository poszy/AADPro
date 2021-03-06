package com.example.luispena.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    // Never pass context into ViewModel instances. Do not store Activity, Fragment, or View instances or their Context in the ViewModel
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;
    public WordViewModel(@NonNull Application application) {

        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }
}
