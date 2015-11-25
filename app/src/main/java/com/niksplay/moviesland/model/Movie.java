package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.niksplay.moviesland.provider.favorite.FavoriteCursor;
import com.niksplay.moviesland.provider.watchlist.WatchlistCursor;
import com.niksplay.moviesland.utils.ArrayUtils;
import com.niksplay.moviesland.utils.Utils;

import java.util.List;

/**
 * Created by nikita on 15.11.15.
 */
public class Movie implements IMedia {

    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("genre_ids")
    public int[] genreIds;
    @SerializedName("genres")
    public Genre[] genres;
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

    public Movie(FavoriteCursor cursor) {
        backdropPath = cursor.getBackdropPath();
        id = cursor.getMovieId() != null ? cursor.getMovieId() : 0;
        originalTitle = cursor.getOriginalTitle();
        overview = cursor.getOverview();
        releaseDate = cursor.getReleaseDate();
        posterPath = cursor.getPosterPath();
        title = cursor.getTitle();
        voteAverage = cursor.getVoteAverage() != null ? cursor.getVoteAverage() : 0;
        voteCount = cursor.getVoteCount() != null ? cursor.getVoteCount() : 0;
        genreIds = ArrayUtils.split(",", cursor.getGenres());
    }

    public Movie(WatchlistCursor cursor) {
        backdropPath = cursor.getBackdropPath();
        id = cursor.getMovieId() != null ? cursor.getMovieId() : 0;
        originalTitle = cursor.getOriginalTitle();
        overview = cursor.getOverview();
        releaseDate = cursor.getReleaseDate();
        posterPath = cursor.getPosterPath();
        title = cursor.getTitle();
        voteAverage = cursor.getVoteAverage() != null ? cursor.getVoteAverage() : 0;
        voteCount = cursor.getVoteCount() != null ? cursor.getVoteCount() : 0;
        genreIds = ArrayUtils.split(",", cursor.getGenres());
    }

    protected Movie(Parcel parcel) {
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


    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public int[] getGenreIds() {
        if (genreIds != null) {
            return genreIds;
        }
        if (genres != null) {
            genreIds = new int[genres.length];
            for (int i = 0; i < genres.length; i++) {
                genreIds[i] = genres[i].id;
            }
        }
        return genreIds;
    }

    @Override
    public Genre[] getGenres() {
        return genres;
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

    @Override
    public Type getType() {
        return Type.MOVIE;
    }
}
