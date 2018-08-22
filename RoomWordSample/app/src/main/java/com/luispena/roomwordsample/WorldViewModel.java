package com.luispena.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WorldViewModel extends AndroidViewModel {

    //  hold a reference to the repository.
    private WordRepository mRepository;

    //cache the list of words.
    private LiveData<List<Word>> mAllWords;

    public WorldViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }
    public void insert(Word word) { mRepository.insert(word); }


}
