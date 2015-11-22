package com.niksplay.moviesland.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 15.11.15.
 */
public class TV extends Movie {
    @SerializedName("first_air_date")
    public String releaseDate;
    @SerializedName("original_name")
    public String originalTitle;
    @SerializedName("name")
    public String title;

    public static final Creator<TV> CREATOR = new Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel parcel) {
            return new TV(parcel);
        }

        @Override
        public TV[] newArray(int i) {
            return new TV[i];
        }
    };


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public Type getType() {
        return Type.TV;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(releaseDate);
        parcel.writeString(originalTitle);
        parcel.writeString(title);
    }

    public TV(Parcel parcel){
        super(parcel);
        releaseDate = parcel.readString();
        originalTitle = parcel.readString();
        title = parcel.readString();
    }


}
