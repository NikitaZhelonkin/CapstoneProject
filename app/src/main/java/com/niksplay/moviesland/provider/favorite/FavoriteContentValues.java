package com.niksplay.moviesland.provider.favorite;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.niksplay.moviesland.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code favorite} table.
 */
public class FavoriteContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FavoriteSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FavoriteSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * backdropPath
     */
    public FavoriteContentValues putBackdropPath(@Nullable String value) {
        mContentValues.put(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteContentValues putBackdropPathNull() {
        mContentValues.putNull(FavoriteColumns.BACKDROP_PATH);
        return this;
    }

    /**
     * movie id
     */
    public FavoriteContentValues putMovieId(@Nullable Long value) {
        mContentValues.put(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteContentValues putMovieIdNull() {
        mContentValues.putNull(FavoriteColumns.MOVIE_ID);
        return this;
    }

    /**
     * original title
     */
    public FavoriteContentValues putOriginalTitle(@Nullable String value) {
        mContentValues.put(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteContentValues putOriginalTitleNull() {
        mContentValues.putNull(FavoriteColumns.ORIGINAL_TITLE);
        return this;
    }

    /**
     * overview
     */
    public FavoriteContentValues putOverview(@Nullable String value) {
        mContentValues.put(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteContentValues putOverviewNull() {
        mContentValues.putNull(FavoriteColumns.OVERVIEW);
        return this;
    }

    /**
     * release date
     */
    public FavoriteContentValues putReleaseDate(@Nullable String value) {
        mContentValues.put(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteContentValues putReleaseDateNull() {
        mContentValues.putNull(FavoriteColumns.RELEASE_DATE);
        return this;
    }

    /**
     * poster path
     */
    public FavoriteContentValues putPosterPath(@Nullable String value) {
        mContentValues.put(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteContentValues putPosterPathNull() {
        mContentValues.putNull(FavoriteColumns.POSTER_PATH);
        return this;
    }

    /**
     * popularity
     */
    public FavoriteContentValues putPopularity(@Nullable Float value) {
        mContentValues.put(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteContentValues putPopularityNull() {
        mContentValues.putNull(FavoriteColumns.POPULARITY);
        return this;
    }

    /**
     * title
     */
    public FavoriteContentValues putTitle(@Nullable String value) {
        mContentValues.put(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteContentValues putTitleNull() {
        mContentValues.putNull(FavoriteColumns.TITLE);
        return this;
    }

    /**
     * vote average
     */
    public FavoriteContentValues putVoteAverage(@Nullable Float value) {
        mContentValues.put(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteContentValues putVoteAverageNull() {
        mContentValues.putNull(FavoriteColumns.VOTE_AVERAGE);
        return this;
    }

    /**
     * vote count
     */
    public FavoriteContentValues putVoteCount(@Nullable Integer value) {
        mContentValues.put(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteContentValues putVoteCountNull() {
        mContentValues.putNull(FavoriteColumns.VOTE_COUNT);
        return this;
    }

    /**
     * media type
     */
    public FavoriteContentValues putMediaType(@Nullable String value) {
        mContentValues.put(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteContentValues putMediaTypeNull() {
        mContentValues.putNull(FavoriteColumns.MEDIA_TYPE);
        return this;
    }

    /**
     * genres
     */
    public FavoriteContentValues putGenres(@Nullable String value) {
        mContentValues.put(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteContentValues putGenresNull() {
        mContentValues.putNull(FavoriteColumns.GENRES);
        return this;
    }
}
