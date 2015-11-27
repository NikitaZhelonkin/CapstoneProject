package com.niksplay.moviesland.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.niksplay.moviesland.model.response.VideosResponse;
import com.niksplay.moviesland.provider.favorite.FavoriteCursor;
import com.niksplay.moviesland.provider.watchlist.WatchlistCursor;
import com.niksplay.moviesland.utils.ArrayUtils;

/**
 * Created by nikita on 15.11.15.
 */
public class Movie extends Media {
    @SerializedName("genres")
    public Genre[] genres;
    public String homepage;
    VideosResponse videos;

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

    protected Movie(Parcel parcel) {
        super(parcel);
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


    @Override
    public int describeContents() {
        return 0;
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
    public String getHomePage() {
        return homepage;
    }

    @Override
    public VideosResponse getVideos() {
        return videos;
    }

    @Override
    public Type getType() {
        return Type.MOVIE;
    }
}
