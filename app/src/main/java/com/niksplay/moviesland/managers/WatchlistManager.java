package com.niksplay.moviesland.managers;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.provider.watchlist.WatchlistContentValues;
import com.niksplay.moviesland.provider.watchlist.WatchlistCursor;
import com.niksplay.moviesland.provider.watchlist.WatchlistSelection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nikita on 24.11.15.
 */
public class WatchlistManager {

    private static final Context mContext = App.getInstance();

    public static boolean isInWatchlist(IMedia movie) {
        Cursor c = null;
        try {
            WatchlistSelection where = new WatchlistSelection();
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

    public static void toggleWatchlist(IMedia media) {
        if (isInWatchlist(media)) {
            removeFromWatchlist(media);
        } else {
            addToWatchlist(media);
        }
    }

    public static void addToWatchlist(IMedia movie) {
        WatchlistContentValues cv = new WatchlistContentValues();
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

    public static void removeFromWatchlist(IMedia media) {
        WatchlistSelection where = new WatchlistSelection();
        where.movieId(media.getId());
        mContext.getContentResolver().delete(where.uri(), where.sel(), where.args());
    }

    public static List<IMedia> getWatchlistMedia() {
        Cursor cursor = mContext.getContentResolver().query(new WatchlistSelection().uri(), null,
                null, null, null);
        if (cursor != null) {
            try {
                List<IMedia> movies = new ArrayList<>();
                while (cursor.moveToNext()) {
                    WatchlistCursor favCursor = new WatchlistCursor(cursor);
                    if (IMedia.Type.MOVIE.toString().equals(favCursor.getMediaType())) {
                        movies.add(new Movie(new WatchlistCursor(cursor)));
                    } else {
                        movies.add(new TV(new WatchlistCursor(cursor)));
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
