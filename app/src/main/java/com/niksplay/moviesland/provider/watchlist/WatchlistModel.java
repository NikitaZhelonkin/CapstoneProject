package com.niksplay.moviesland.provider.watchlist;

import com.niksplay.moviesland.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A watchlist movies.
 */
public interface WatchlistModel extends BaseModel {

    /**
     * backdropPath
     * Can be {@code null}.
     */
    @Nullable
    String getBackdropPath();

    /**
     * movie id
     * Can be {@code null}.
     */
    @Nullable
    Long getMovieId();

    /**
     * original title
     * Can be {@code null}.
     */
    @Nullable
    String getOriginalTitle();

    /**
     * overview
     * Can be {@code null}.
     */
    @Nullable
    String getOverview();

    /**
     * release date
     * Can be {@code null}.
     */
    @Nullable
    String getReleaseDate();

    /**
     * poster path
     * Can be {@code null}.
     */
    @Nullable
    String getPosterPath();

    /**
     * popularity
     * Can be {@code null}.
     */
    @Nullable
    Float getPopularity();

    /**
     * title
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * vote average
     * Can be {@code null}.
     */
    @Nullable
    Float getVoteAverage();

    /**
     * vote count
     * Can be {@code null}.
     */
    @Nullable
    Integer getVoteCount();

    /**
     * media type
     * Can be {@code null}.
     */
    @Nullable
    String getMediaType();

    /**
     * genres
     * Can be {@code null}.
     */
    @Nullable
    String getGenres();
}
