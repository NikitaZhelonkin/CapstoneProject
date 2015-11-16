package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.niksplay.moviesland.network.MoviesService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nikita on 15.11.15.
 */
public class Movie implements IMovie, Parcelable {

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


    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public int[] getGenreIds() {
        return genreIds;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public float getPopularity() {
        return popularity;
    }

    @Override
    public boolean isVideo() {
        return video;
    }

    @Override
    public float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
