package com.niksplay.moviesland.provider.favorite;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.niksplay.moviesland.provider.base.AbstractSelection;

/**
 * Selection for the {@code favorite} table.
 */
public class FavoriteSelection extends AbstractSelection<FavoriteSelection> {
    @Override
    protected Uri baseUri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoriteCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoriteCursor query(Context context) {
        return query(context, null);
    }


    public FavoriteSelection id(long... value) {
        addEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection idNot(long... value) {
        addNotEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection orderById(boolean desc) {
        orderBy("favorite." + FavoriteColumns._ID, desc);
        return this;
    }

    public FavoriteSelection orderById() {
        return orderById(false);
    }

    public FavoriteSelection backdropPath(String... value) {
        addEquals(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection backdropPathNot(String... value) {
        addNotEquals(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection backdropPathLike(String... value) {
        addLike(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection backdropPathContains(String... value) {
        addContains(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection backdropPathStartsWith(String... value) {
        addStartsWith(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection backdropPathEndsWith(String... value) {
        addEndsWith(FavoriteColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteSelection orderByBackdropPath(boolean desc) {
        orderBy(FavoriteColumns.BACKDROP_PATH, desc);
        return this;
    }

    public FavoriteSelection orderByBackdropPath() {
        orderBy(FavoriteColumns.BACKDROP_PATH, false);
        return this;
    }

    public FavoriteSelection movieId(Long... value) {
        addEquals(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteSelection movieIdNot(Long... value) {
        addNotEquals(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteSelection movieIdGt(long value) {
        addGreaterThan(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteSelection movieIdLt(long value) {
        addLessThan(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteSelection movieIdLtEq(long value) {
        addLessThanOrEquals(FavoriteColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteSelection orderByMovieId(boolean desc) {
        orderBy(FavoriteColumns.MOVIE_ID, desc);
        return this;
    }

    public FavoriteSelection orderByMovieId() {
        orderBy(FavoriteColumns.MOVIE_ID, false);
        return this;
    }

    public FavoriteSelection originalTitle(String... value) {
        addEquals(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection originalTitleNot(String... value) {
        addNotEquals(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection originalTitleLike(String... value) {
        addLike(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection originalTitleContains(String... value) {
        addContains(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection originalTitleStartsWith(String... value) {
        addStartsWith(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection originalTitleEndsWith(String... value) {
        addEndsWith(FavoriteColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteSelection orderByOriginalTitle(boolean desc) {
        orderBy(FavoriteColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public FavoriteSelection orderByOriginalTitle() {
        orderBy(FavoriteColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public FavoriteSelection overview(String... value) {
        addEquals(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection overviewNot(String... value) {
        addNotEquals(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection overviewLike(String... value) {
        addLike(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection overviewContains(String... value) {
        addContains(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection overviewStartsWith(String... value) {
        addStartsWith(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection overviewEndsWith(String... value) {
        addEndsWith(FavoriteColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteSelection orderByOverview(boolean desc) {
        orderBy(FavoriteColumns.OVERVIEW, desc);
        return this;
    }

    public FavoriteSelection orderByOverview() {
        orderBy(FavoriteColumns.OVERVIEW, false);
        return this;
    }

    public FavoriteSelection releaseDate(String... value) {
        addEquals(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateNot(String... value) {
        addNotEquals(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateLike(String... value) {
        addLike(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateContains(String... value) {
        addContains(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateStartsWith(String... value) {
        addStartsWith(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateEndsWith(String... value) {
        addEndsWith(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection orderByReleaseDate(boolean desc) {
        orderBy(FavoriteColumns.RELEASE_DATE, desc);
        return this;
    }

    public FavoriteSelection orderByReleaseDate() {
        orderBy(FavoriteColumns.RELEASE_DATE, false);
        return this;
    }

    public FavoriteSelection posterPath(String... value) {
        addEquals(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection posterPathNot(String... value) {
        addNotEquals(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection posterPathLike(String... value) {
        addLike(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection posterPathContains(String... value) {
        addContains(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection posterPathStartsWith(String... value) {
        addStartsWith(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection posterPathEndsWith(String... value) {
        addEndsWith(FavoriteColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteSelection orderByPosterPath(boolean desc) {
        orderBy(FavoriteColumns.POSTER_PATH, desc);
        return this;
    }

    public FavoriteSelection orderByPosterPath() {
        orderBy(FavoriteColumns.POSTER_PATH, false);
        return this;
    }

    public FavoriteSelection popularity(Float... value) {
        addEquals(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection popularityNot(Float... value) {
        addNotEquals(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection popularityGt(float value) {
        addGreaterThan(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection popularityGtEq(float value) {
        addGreaterThanOrEquals(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection popularityLt(float value) {
        addLessThan(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection popularityLtEq(float value) {
        addLessThanOrEquals(FavoriteColumns.POPULARITY, value);
        return this;
    }

    public FavoriteSelection orderByPopularity(boolean desc) {
        orderBy(FavoriteColumns.POPULARITY, desc);
        return this;
    }

    public FavoriteSelection orderByPopularity() {
        orderBy(FavoriteColumns.POPULARITY, false);
        return this;
    }

    public FavoriteSelection title(String... value) {
        addEquals(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleNot(String... value) {
        addNotEquals(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleLike(String... value) {
        addLike(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleContains(String... value) {
        addContains(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleStartsWith(String... value) {
        addStartsWith(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleEndsWith(String... value) {
        addEndsWith(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection orderByTitle(boolean desc) {
        orderBy(FavoriteColumns.TITLE, desc);
        return this;
    }

    public FavoriteSelection orderByTitle() {
        orderBy(FavoriteColumns.TITLE, false);
        return this;
    }

    public FavoriteSelection voteAverage(Float... value) {
        addEquals(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection voteAverageNot(Float... value) {
        addNotEquals(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection voteAverageGt(float value) {
        addGreaterThan(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection voteAverageGtEq(float value) {
        addGreaterThanOrEquals(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection voteAverageLt(float value) {
        addLessThan(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection voteAverageLtEq(float value) {
        addLessThanOrEquals(FavoriteColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteSelection orderByVoteAverage(boolean desc) {
        orderBy(FavoriteColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public FavoriteSelection orderByVoteAverage() {
        orderBy(FavoriteColumns.VOTE_AVERAGE, false);
        return this;
    }

    public FavoriteSelection voteCount(Integer... value) {
        addEquals(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection voteCountNot(Integer... value) {
        addNotEquals(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection voteCountGt(int value) {
        addGreaterThan(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection voteCountGtEq(int value) {
        addGreaterThanOrEquals(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection voteCountLt(int value) {
        addLessThan(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection voteCountLtEq(int value) {
        addLessThanOrEquals(FavoriteColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteSelection orderByVoteCount(boolean desc) {
        orderBy(FavoriteColumns.VOTE_COUNT, desc);
        return this;
    }

    public FavoriteSelection orderByVoteCount() {
        orderBy(FavoriteColumns.VOTE_COUNT, false);
        return this;
    }

    public FavoriteSelection mediaType(String... value) {
        addEquals(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteSelection mediaTypeNot(String... value) {
        addNotEquals(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteSelection mediaTypeLike(String... value) {
        addLike(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteSelection mediaTypeContains(String... value) {
        addContains(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteSelection mediaTypeStartsWith(String... value) {
        addStartsWith(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteSelection mediaTypeEndsWith(String... value) {
        addEndsWith(FavoriteColumns.MEDIA_TYPE, value);
        return this;
    }

    public FavoriteSelection orderByMediaType(boolean desc) {
        orderBy(FavoriteColumns.MEDIA_TYPE, desc);
        return this;
    }

    public FavoriteSelection orderByMediaType() {
        orderBy(FavoriteColumns.MEDIA_TYPE, false);
        return this;
    }

    public FavoriteSelection genres(String... value) {
        addEquals(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteSelection genresNot(String... value) {
        addNotEquals(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteSelection genresLike(String... value) {
        addLike(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteSelection genresContains(String... value) {
        addContains(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteSelection genresStartsWith(String... value) {
        addStartsWith(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteSelection genresEndsWith(String... value) {
        addEndsWith(FavoriteColumns.GENRES, value);
        return this;
    }

    public FavoriteSelection orderByGenres(boolean desc) {
        orderBy(FavoriteColumns.GENRES, desc);
        return this;
    }

    public FavoriteSelection orderByGenres() {
        orderBy(FavoriteColumns.GENRES, false);
        return this;
    }
}
