package com.example.colorapp;

import android.os.Parcelable;

abstract class ClothingItem{
    protected int color;
    protected String type;
    protected String season;

    public ClothingItem(int color, String season, String type){
        this.color = color;
        this.season = season;
        this.type = type;
    }
}
