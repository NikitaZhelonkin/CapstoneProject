package com.niksplay.moviesland.provider.watchlist;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.niksplay.moviesland.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code watchlist} table.
 */
public class WatchlistCursor extends AbstractCursor implements WatchlistModel {
    public WatchlistCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(WatchlistColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * backdropPath
     * Can be {@code null}.
     */
    @Nullable
    public String getBackdropPath() {
        String res = getStringOrNull(WatchlistColumns.BACKDROP_PATH);
        return res;
    }

    /**
     * movie id
     * Can be {@code null}.
     */
    @Nullable
    public Long getMovieId() {
        Long res = getLongOrNull(WatchlistColumns.MOVIE_ID);
        return res;
    }

    /**
     * original title
     * Can be {@code null}.
     */
    @Nullable
    public String getOriginalTitle() {
        String res = getStringOrNull(WatchlistColumns.ORIGINAL_TITLE);
        return res;
    }

    /**
     * overview
     * Can be {@code null}.
     */
    @Nullable
    public String getOverview() {
        String res = getStringOrNull(WatchlistColumns.OVERVIEW);
        return res;
    }

    /**
     * release date
     * Can be {@code null}.
     */
    @Nullable
    public String getReleaseDate() {
        String res = getStringOrNull(WatchlistColumns.RELEASE_DATE);
        return res;
    }

    /**
     * poster path
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterPath() {
        String res = getStringOrNull(WatchlistColumns.POSTER_PATH);
        return res;
    }

    /**
     * popularity
     * Can be {@code null}.
     */
    @Nullable
    public Float getPopularity() {
        Float res = getFloatOrNull(WatchlistColumns.POPULARITY);
        return res;
    }

    /**
     * title
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(WatchlistColumns.TITLE);
        return res;
    }

    /**
     * vote average
     * Can be {@code null}.
     */
    @Nullable
    public Float getVoteAverage() {
        Float res = getFloatOrNull(WatchlistColumns.VOTE_AVERAGE);
        return res;
    }

    /**
     * vote count
     * Can be {@code null}.
     */
    @Nullable
    public Integer getVoteCount() {
        Integer res = getIntegerOrNull(WatchlistColumns.VOTE_COUNT);
        return res;
    }

    /**
     * media type
     * Can be {@code null}.
     */
    @Nullable
    public String getMediaType() {
        String res = getStringOrNull(WatchlistColumns.MEDIA_TYPE);
        return res;
    }

    /**
     * genres
     * Can be {@code null}.
     */
    @Nullable
    public String getGenres() {
        String res = getStringOrNull(WatchlistColumns.GENRES);
        return res;
    }
}
