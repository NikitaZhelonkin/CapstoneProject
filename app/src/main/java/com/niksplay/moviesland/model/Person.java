package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikita on 15.11.15.
 */
public class Person implements Parcelable {

    public long id;
    public String biography;
    public String birthday;
    public String name;
    @SerializedName("place_of_birth")
    public String placeOfBirth;
    @SerializedName("profile_path")
    public String profilePath;
    @SerializedName("known_for")
    public List<Movie> knownFor;

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel parcel) {
            return new Person(parcel);
        }

        @Override
        public Person[] newArray(int i) {
            return new Person[i];
        }
    };

    public Person(Parcel parcel){
        id = parcel.readLong();
        biography = parcel.readString();
        birthday = parcel.readString();
        name = parcel.readString();
        placeOfBirth = parcel.readString();
        profilePath = parcel.readString();
        knownFor = parcel.createTypedArrayList(Movie.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(biography);
        parcel.writeString(birthday);
        parcel.writeString(name);
        parcel.writeString(placeOfBirth);
        parcel.writeString(profilePath);
        parcel.writeTypedList(knownFor);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
