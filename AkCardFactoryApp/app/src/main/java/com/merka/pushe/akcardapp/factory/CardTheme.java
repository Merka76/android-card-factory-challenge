package com.merka.pushe.akcardapp.factory;

import android.content.Context;

/**
 * Created by Akram Shokri on 16-02-09, 12:46 PM.
 */
public enum CardTheme {
    SPORT,
    ART,
    FUN;

    private Context mContext;

    /*CardTheme() {

    }

    CardTheme(Context mContext) {
        this.mContext = mContext;
    }*/



    public String getFunIconName(){
        return "clown";
    }

    public String getArtIconName(){
        return "artist";
    }

    public String getSportIconName(){
        return "football";
    }
}
