package com.niksplay.moviesland.provider.watchlist;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.niksplay.moviesland.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code watchlist} table.
 */
public class WatchlistContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return WatchlistColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable WatchlistSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable WatchlistSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * backdropPath
     */
    public WatchlistContentValues putBackdropPath(@Nullable String value) {
        mContentValues.put(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistContentValues putBackdropPathNull() {
        mContentValues.putNull(WatchlistColumns.BACKDROP_PATH);
        return this;
    }

    /**
     * movie id
     */
    public WatchlistContentValues putMovieId(@Nullable Long value) {
        mContentValues.put(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistContentValues putMovieIdNull() {
        mContentValues.putNull(WatchlistColumns.MOVIE_ID);
        return this;
    }

    /**
     * original title
     */
    public WatchlistContentValues putOriginalTitle(@Nullable String value) {
        mContentValues.put(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistContentValues putOriginalTitleNull() {
        mContentValues.putNull(WatchlistColumns.ORIGINAL_TITLE);
        return this;
    }

    /**
     * overview
     */
    public WatchlistContentValues putOverview(@Nullable String value) {
        mContentValues.put(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistContentValues putOverviewNull() {
        mContentValues.putNull(WatchlistColumns.OVERVIEW);
        return this;
    }

    /**
     * release date
     */
    public WatchlistContentValues putReleaseDate(@Nullable String value) {
        mContentValues.put(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistContentValues putReleaseDateNull() {
        mContentValues.putNull(WatchlistColumns.RELEASE_DATE);
        return this;
    }

    /**
     * poster path
     */
    public WatchlistContentValues putPosterPath(@Nullable String value) {
        mContentValues.put(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistContentValues putPosterPathNull() {
        mContentValues.putNull(WatchlistColumns.POSTER_PATH);
        return this;
    }

    /**
     * popularity
     */
    public WatchlistContentValues putPopularity(@Nullable Float value) {
        mContentValues.put(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistContentValues putPopularityNull() {
        mContentValues.putNull(WatchlistColumns.POPULARITY);
        return this;
    }

    /**
     * title
     */
    public WatchlistContentValues putTitle(@Nullable String value) {
        mContentValues.put(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistContentValues putTitleNull() {
        mContentValues.putNull(WatchlistColumns.TITLE);
        return this;
    }

    /**
     * vote average
     */
    public WatchlistContentValues putVoteAverage(@Nullable Float value) {
        mContentValues.put(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistContentValues putVoteAverageNull() {
        mContentValues.putNull(WatchlistColumns.VOTE_AVERAGE);
        return this;
    }

    /**
     * vote count
     */
    public WatchlistContentValues putVoteCount(@Nullable Integer value) {
        mContentValues.put(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistContentValues putVoteCountNull() {
        mContentValues.putNull(WatchlistColumns.VOTE_COUNT);
        return this;
    }

    /**
     * media type
     */
    public WatchlistContentValues putMediaType(@Nullable String value) {
        mContentValues.put(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistContentValues putMediaTypeNull() {
        mContentValues.putNull(WatchlistColumns.MEDIA_TYPE);
        return this;
    }

    /**
     * genres
     */
    public WatchlistContentValues putGenres(@Nullable String value) {
        mContentValues.put(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistContentValues putGenresNull() {
        mContentValues.putNull(WatchlistColumns.GENRES);
        return this;
    }
}
