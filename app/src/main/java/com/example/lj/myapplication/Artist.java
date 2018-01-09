package com.example.lj.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Artist implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
       public Artist createFromParcel(Parcel in) {
           return new Artist(in);
       }

       public Artist[] newArray(int size) {
           return new Artist[size];
       }
    };

    public String name;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(Parcel in) {
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
