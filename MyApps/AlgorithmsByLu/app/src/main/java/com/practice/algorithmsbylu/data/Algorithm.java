package com.practice.algorithmsbylu.data;

public class Algorithm {

    private String mTitle;
    private String mDescription;

    public Algorithm(){}
    public Algorithm(String title, String description){
        this.mTitle = title;
        this.mDescription = description;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String name) {
        this.mTitle = name;
    }

    public String getmDescription(){
        return mDescription;
    }

    public void setmDescription(String name) {
        this.mDescription = name;
    }
}
