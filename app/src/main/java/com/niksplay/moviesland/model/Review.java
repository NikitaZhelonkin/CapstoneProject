package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nikita on 15.11.15.
 */
public class Review implements Parcelable {

    public String id;
    public String author;
    public String content;
    public String url;

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }

        @Override
        public Review[] newArray(int i) {
            return new Review[i];
        }
    };

    public Review() {
    }

    public Review(Parcel parcel) {
        id = parcel.readString();
        author = parcel.readString();
        content = parcel.readString();
        url = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
