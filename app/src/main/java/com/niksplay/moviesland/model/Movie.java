package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nikita on 15.11.15.
 */
public class Movie implements Parcelable {

    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("genre_ids")
    public int[] genreIds;
    public long id;
    @SerializedName("original_title")
    public String originalTitle;
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("poster_path")
    public String posterPath;
    public float popularity;
    public String title;
    public boolean video;
    @SerializedName("vote_average")
    public float voteAverage;
    @SerializedName("vote_count")
    public int voteCount;

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

    public Movie() {

    }

    private Movie(Parcel parcel) {
        adult = parcel.readInt() == 1;
        backdropPath = parcel.readString();
        genreIds = parcel.createIntArray();
        id = parcel.readLong();
        originalTitle = parcel.readString();
        overview = parcel.readString();
        releaseDate = parcel.readString();
        posterPath = parcel.readString();
        popularity = parcel.readFloat();
        title = parcel.readString();
        video = parcel.readInt() == 1;
        voteAverage = parcel.readFloat();
        voteCount = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(adult ? 1 : 0);
        parcel.writeString(backdropPath);
        parcel.writeIntArray(genreIds);
        parcel.writeLong(id);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeFloat(popularity);
        parcel.writeString(title);
        parcel.writeInt(video ? 1 : 0);
        parcel.writeFloat(voteAverage);
        parcel.writeInt(voteCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getReleaseYear() {
        try {
            if (TextUtils.isEmpty(releaseDate)) {
                return 0;
            }
            Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(releaseDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            return 0;
        }

    }


}
