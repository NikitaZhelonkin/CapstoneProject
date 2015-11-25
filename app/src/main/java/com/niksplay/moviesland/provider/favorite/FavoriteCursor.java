package com.niksplay.moviesland.provider.favorite;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.niksplay.moviesland.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code favorite} table.
 */
public class FavoriteCursor extends AbstractCursor implements FavoriteModel {
    public FavoriteCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FavoriteColumns._ID);
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
        String res = getStringOrNull(FavoriteColumns.BACKDROP_PATH);
        return res;
    }

    /**
     * movie id
     * Can be {@code null}.
     */
    @Nullable
    public Long getMovieId() {
        Long res = getLongOrNull(FavoriteColumns.MOVIE_ID);
        return res;
    }

    /**
     * original title
     * Can be {@code null}.
     */
    @Nullable
    public String getOriginalTitle() {
        String res = getStringOrNull(FavoriteColumns.ORIGINAL_TITLE);
        return res;
    }

    /**
     * overview
     * Can be {@code null}.
     */
    @Nullable
    public String getOverview() {
        String res = getStringOrNull(FavoriteColumns.OVERVIEW);
        return res;
    }

    /**
     * release date
     * Can be {@code null}.
     */
    @Nullable
    public String getReleaseDate() {
        String res = getStringOrNull(FavoriteColumns.RELEASE_DATE);
        return res;
    }

    /**
     * poster path
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterPath() {
        String res = getStringOrNull(FavoriteColumns.POSTER_PATH);
        return res;
    }

    /**
     * popularity
     * Can be {@code null}.
     */
    @Nullable
    public Float getPopularity() {
        Float res = getFloatOrNull(FavoriteColumns.POPULARITY);
        return res;
    }

    /**
     * title
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(FavoriteColumns.TITLE);
        return res;
    }

    /**
     * vote average
     * Can be {@code null}.
     */
    @Nullable
    public Float getVoteAverage() {
        Float res = getFloatOrNull(FavoriteColumns.VOTE_AVERAGE);
        return res;
    }

    /**
     * vote count
     * Can be {@code null}.
     */
    @Nullable
    public Integer getVoteCount() {
        Integer res = getIntegerOrNull(FavoriteColumns.VOTE_COUNT);
        return res;
    }

    /**
     * media type
     * Can be {@code null}.
     */
    @Nullable
    public String getMediaType() {
        String res = getStringOrNull(FavoriteColumns.MEDIA_TYPE);
        return res;
    }

    /**
     * genres
     * Can be {@code null}.
     */
    @Nullable
    public String getGenres() {
        String res = getStringOrNull(FavoriteColumns.GENRES);
        return res;
    }
}
