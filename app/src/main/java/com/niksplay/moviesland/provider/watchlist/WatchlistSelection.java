package com.niksplay.moviesland.provider.watchlist;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.niksplay.moviesland.provider.base.AbstractSelection;

/**
 * Selection for the {@code watchlist} table.
 */
public class WatchlistSelection extends AbstractSelection<WatchlistSelection> {
    @Override
    protected Uri baseUri() {
        return WatchlistColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WatchlistCursor} object, which is positioned before the first entry, or null.
     */
    public WatchlistCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WatchlistCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public WatchlistCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WatchlistCursor} object, which is positioned before the first entry, or null.
     */
    public WatchlistCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WatchlistCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public WatchlistCursor query(Context context) {
        return query(context, null);
    }


    public WatchlistSelection id(long... value) {
        addEquals("watchlist." + WatchlistColumns._ID, toObjectArray(value));
        return this;
    }

    public WatchlistSelection idNot(long... value) {
        addNotEquals("watchlist." + WatchlistColumns._ID, toObjectArray(value));
        return this;
    }

    public WatchlistSelection orderById(boolean desc) {
        orderBy("watchlist." + WatchlistColumns._ID, desc);
        return this;
    }

    public WatchlistSelection orderById() {
        return orderById(false);
    }

    public WatchlistSelection backdropPath(String... value) {
        addEquals(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistSelection backdropPathNot(String... value) {
        addNotEquals(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistSelection backdropPathLike(String... value) {
        addLike(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistSelection backdropPathContains(String... value) {
        addContains(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistSelection backdropPathStartsWith(String... value) {
        addStartsWith(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistSelection backdropPathEndsWith(String... value) {
        addEndsWith(WatchlistColumns.BACKDROP_PATH, value);
        return this;
    }

    public WatchlistSelection orderByBackdropPath(boolean desc) {
        orderBy(WatchlistColumns.BACKDROP_PATH, desc);
        return this;
    }

    public WatchlistSelection orderByBackdropPath() {
        orderBy(WatchlistColumns.BACKDROP_PATH, false);
        return this;
    }

    public WatchlistSelection movieId(Long... value) {
        addEquals(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistSelection movieIdNot(Long... value) {
        addNotEquals(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistSelection movieIdGt(long value) {
        addGreaterThan(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistSelection movieIdLt(long value) {
        addLessThan(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistSelection movieIdLtEq(long value) {
        addLessThanOrEquals(WatchlistColumns.MOVIE_ID, value);
        return this;
    }

    public WatchlistSelection orderByMovieId(boolean desc) {
        orderBy(WatchlistColumns.MOVIE_ID, desc);
        return this;
    }

    public WatchlistSelection orderByMovieId() {
        orderBy(WatchlistColumns.MOVIE_ID, false);
        return this;
    }

    public WatchlistSelection originalTitle(String... value) {
        addEquals(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistSelection originalTitleNot(String... value) {
        addNotEquals(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistSelection originalTitleLike(String... value) {
        addLike(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistSelection originalTitleContains(String... value) {
        addContains(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistSelection originalTitleStartsWith(String... value) {
        addStartsWith(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistSelection originalTitleEndsWith(String... value) {
        addEndsWith(WatchlistColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public WatchlistSelection orderByOriginalTitle(boolean desc) {
        orderBy(WatchlistColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public WatchlistSelection orderByOriginalTitle() {
        orderBy(WatchlistColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public WatchlistSelection overview(String... value) {
        addEquals(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistSelection overviewNot(String... value) {
        addNotEquals(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistSelection overviewLike(String... value) {
        addLike(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistSelection overviewContains(String... value) {
        addContains(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistSelection overviewStartsWith(String... value) {
        addStartsWith(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistSelection overviewEndsWith(String... value) {
        addEndsWith(WatchlistColumns.OVERVIEW, value);
        return this;
    }

    public WatchlistSelection orderByOverview(boolean desc) {
        orderBy(WatchlistColumns.OVERVIEW, desc);
        return this;
    }

    public WatchlistSelection orderByOverview() {
        orderBy(WatchlistColumns.OVERVIEW, false);
        return this;
    }

    public WatchlistSelection releaseDate(String... value) {
        addEquals(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistSelection releaseDateNot(String... value) {
        addNotEquals(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistSelection releaseDateLike(String... value) {
        addLike(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistSelection releaseDateContains(String... value) {
        addContains(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistSelection releaseDateStartsWith(String... value) {
        addStartsWith(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistSelection releaseDateEndsWith(String... value) {
        addEndsWith(WatchlistColumns.RELEASE_DATE, value);
        return this;
    }

    public WatchlistSelection orderByReleaseDate(boolean desc) {
        orderBy(WatchlistColumns.RELEASE_DATE, desc);
        return this;
    }

    public WatchlistSelection orderByReleaseDate() {
        orderBy(WatchlistColumns.RELEASE_DATE, false);
        return this;
    }

    public WatchlistSelection posterPath(String... value) {
        addEquals(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistSelection posterPathNot(String... value) {
        addNotEquals(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistSelection posterPathLike(String... value) {
        addLike(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistSelection posterPathContains(String... value) {
        addContains(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistSelection posterPathStartsWith(String... value) {
        addStartsWith(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistSelection posterPathEndsWith(String... value) {
        addEndsWith(WatchlistColumns.POSTER_PATH, value);
        return this;
    }

    public WatchlistSelection orderByPosterPath(boolean desc) {
        orderBy(WatchlistColumns.POSTER_PATH, desc);
        return this;
    }

    public WatchlistSelection orderByPosterPath() {
        orderBy(WatchlistColumns.POSTER_PATH, false);
        return this;
    }

    public WatchlistSelection popularity(Float... value) {
        addEquals(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistSelection popularityNot(Float... value) {
        addNotEquals(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistSelection popularityGt(float value) {
        addGreaterThan(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistSelection popularityGtEq(float value) {
        addGreaterThanOrEquals(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistSelection popularityLt(float value) {
        addLessThan(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistSelection popularityLtEq(float value) {
        addLessThanOrEquals(WatchlistColumns.POPULARITY, value);
        return this;
    }

    public WatchlistSelection orderByPopularity(boolean desc) {
        orderBy(WatchlistColumns.POPULARITY, desc);
        return this;
    }

    public WatchlistSelection orderByPopularity() {
        orderBy(WatchlistColumns.POPULARITY, false);
        return this;
    }

    public WatchlistSelection title(String... value) {
        addEquals(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistSelection titleNot(String... value) {
        addNotEquals(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistSelection titleLike(String... value) {
        addLike(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistSelection titleContains(String... value) {
        addContains(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistSelection titleStartsWith(String... value) {
        addStartsWith(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistSelection titleEndsWith(String... value) {
        addEndsWith(WatchlistColumns.TITLE, value);
        return this;
    }

    public WatchlistSelection orderByTitle(boolean desc) {
        orderBy(WatchlistColumns.TITLE, desc);
        return this;
    }

    public WatchlistSelection orderByTitle() {
        orderBy(WatchlistColumns.TITLE, false);
        return this;
    }

    public WatchlistSelection voteAverage(Float... value) {
        addEquals(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistSelection voteAverageNot(Float... value) {
        addNotEquals(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistSelection voteAverageGt(float value) {
        addGreaterThan(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistSelection voteAverageGtEq(float value) {
        addGreaterThanOrEquals(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistSelection voteAverageLt(float value) {
        addLessThan(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistSelection voteAverageLtEq(float value) {
        addLessThanOrEquals(WatchlistColumns.VOTE_AVERAGE, value);
        return this;
    }

    public WatchlistSelection orderByVoteAverage(boolean desc) {
        orderBy(WatchlistColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public WatchlistSelection orderByVoteAverage() {
        orderBy(WatchlistColumns.VOTE_AVERAGE, false);
        return this;
    }

    public WatchlistSelection voteCount(Integer... value) {
        addEquals(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistSelection voteCountNot(Integer... value) {
        addNotEquals(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistSelection voteCountGt(int value) {
        addGreaterThan(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistSelection voteCountGtEq(int value) {
        addGreaterThanOrEquals(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistSelection voteCountLt(int value) {
        addLessThan(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistSelection voteCountLtEq(int value) {
        addLessThanOrEquals(WatchlistColumns.VOTE_COUNT, value);
        return this;
    }

    public WatchlistSelection orderByVoteCount(boolean desc) {
        orderBy(WatchlistColumns.VOTE_COUNT, desc);
        return this;
    }

    public WatchlistSelection orderByVoteCount() {
        orderBy(WatchlistColumns.VOTE_COUNT, false);
        return this;
    }

    public WatchlistSelection mediaType(String... value) {
        addEquals(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistSelection mediaTypeNot(String... value) {
        addNotEquals(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistSelection mediaTypeLike(String... value) {
        addLike(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistSelection mediaTypeContains(String... value) {
        addContains(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistSelection mediaTypeStartsWith(String... value) {
        addStartsWith(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistSelection mediaTypeEndsWith(String... value) {
        addEndsWith(WatchlistColumns.MEDIA_TYPE, value);
        return this;
    }

    public WatchlistSelection orderByMediaType(boolean desc) {
        orderBy(WatchlistColumns.MEDIA_TYPE, desc);
        return this;
    }

    public WatchlistSelection orderByMediaType() {
        orderBy(WatchlistColumns.MEDIA_TYPE, false);
        return this;
    }

    public WatchlistSelection genres(String... value) {
        addEquals(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistSelection genresNot(String... value) {
        addNotEquals(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistSelection genresLike(String... value) {
        addLike(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistSelection genresContains(String... value) {
        addContains(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistSelection genresStartsWith(String... value) {
        addStartsWith(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistSelection genresEndsWith(String... value) {
        addEndsWith(WatchlistColumns.GENRES, value);
        return this;
    }

    public WatchlistSelection orderByGenres(boolean desc) {
        orderBy(WatchlistColumns.GENRES, desc);
        return this;
    }

    public WatchlistSelection orderByGenres() {
        orderBy(WatchlistColumns.GENRES, false);
        return this;
    }
}
