package com.niksplay.moviesland.managers;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.provider.favorite.FavoriteContentValues;
import com.niksplay.moviesland.provider.favorite.FavoriteCursor;
import com.niksplay.moviesland.provider.favorite.FavoriteSelection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nikita on 24.11.15.
 */
public class FavoriteManager {

    private static final Context mContext = App.getInstance();

    public static boolean isFavorite(IMedia movie) {
        Cursor c = null;
        try {
            FavoriteSelection where = new FavoriteSelection();
            where.movieId(movie.getId());
            c = mContext.getContentResolver().query(where.uri(), null,
                    where.sel(), where.args(), null);
            return c != null && c.moveToFirst();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public static void toggleFavorite(IMedia media) {
        if (isFavorite(media)) {
            removeFromFavorite(media);
        } else {
            addToFavorite(media);
        }
    }

    public static void addToFavorite(IMedia movie) {
        FavoriteContentValues cv = new FavoriteContentValues();
        cv.putBackdropPath(movie.getBackdropPath());
        cv.putMovieId(movie.getId());
        cv.putOriginalTitle(movie.getOriginalTitle());
        cv.putOverview(movie.getOverview());
        cv.putReleaseDate(movie.getReleaseDate());
        cv.putPosterPath(movie.getPosterPath());
        cv.putPopularity(movie.getPopularity());
        cv.putTitle(movie.getTitle());
        cv.putVoteAverage(movie.getVoteAverage());
        cv.putVoteCount(movie.getVoteCount());
        cv.putMediaType(movie.getType().toString());
        if (movie.getGenreIds() != null && movie.getGenreIds().length > 0) {
            cv.putGenres(TextUtils.join(",", Arrays.toString(movie.getGenreIds()).split("[\\[\\]]")[1].split(", ")));
        } else {
            cv.putGenres("");
        }
        mContext.getContentResolver().insert(cv.uri(), cv.values());
    }

    public static void removeFromFavorite(IMedia media) {
        FavoriteSelection where = new FavoriteSelection();
        where.movieId(media.getId());
        mContext.getContentResolver().delete(where.uri(), where.sel(), where.args());
    }

    public static List<IMedia> getFavoriteMedia() {
        Cursor cursor = mContext.getContentResolver().query(new FavoriteSelection().uri(), null,
                null, null, null);
        if (cursor != null) {
            try {
                List<IMedia> movies = new ArrayList<>();
                while (cursor.moveToNext()) {
                    FavoriteCursor favCursor = new FavoriteCursor(cursor);
                    if (IMedia.Type.MOVIE.toString().equals(favCursor.getMediaType())) {
                        movies.add(new Movie(new FavoriteCursor(cursor)));
                    } else {
                        movies.add(new TV(new FavoriteCursor(cursor)));
                    }
                }
                return movies;
            } finally {
                cursor.close();
            }
        }
        return null;
    }
}

