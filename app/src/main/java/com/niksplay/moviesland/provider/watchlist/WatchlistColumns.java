package com.niksplay.moviesland.provider.watchlist;

import android.net.Uri;
import android.provider.BaseColumns;

import com.niksplay.moviesland.provider.MediaProvider;
import com.niksplay.moviesland.provider.favorite.FavoriteColumns;
import com.niksplay.moviesland.provider.watchlist.WatchlistColumns;

/**
 * A watchlist movies.
 */
public class WatchlistColumns implements BaseColumns {
    public static final String TABLE_NAME = "watchlist";
    public static final Uri CONTENT_URI = Uri.parse(MediaProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * backdropPath
     */
    public static final String BACKDROP_PATH = "backdrop_path";

    /**
     * movie id
     */
    public static final String MOVIE_ID = "movie_id";

    /**
     * original title
     */
    public static final String ORIGINAL_TITLE = "original_title";

    /**
     * overview
     */
    public static final String OVERVIEW = "overview";

    /**
     * release date
     */
    public static final String RELEASE_DATE = "release_date";

    /**
     * poster path
     */
    public static final String POSTER_PATH = "poster_path";

    /**
     * popularity
     */
    public static final String POPULARITY = "popularity";

    /**
     * title
     */
    public static final String TITLE = "title";

    /**
     * vote average
     */
    public static final String VOTE_AVERAGE = "vote_average";

    /**
     * vote count
     */
    public static final String VOTE_COUNT = "vote_count";

    /**
     * media type
     */
    public static final String MEDIA_TYPE = "media_type";

    /**
     * genres
     */
    public static final String GENRES = "genres";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            BACKDROP_PATH,
            MOVIE_ID,
            ORIGINAL_TITLE,
            OVERVIEW,
            RELEASE_DATE,
            POSTER_PATH,
            POPULARITY,
            TITLE,
            VOTE_AVERAGE,
            VOTE_COUNT,
            MEDIA_TYPE,
            GENRES
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(BACKDROP_PATH) || c.contains("." + BACKDROP_PATH)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(ORIGINAL_TITLE) || c.contains("." + ORIGINAL_TITLE)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(POSTER_PATH) || c.contains("." + POSTER_PATH)) return true;
            if (c.equals(POPULARITY) || c.contains("." + POPULARITY)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
            if (c.equals(VOTE_COUNT) || c.contains("." + VOTE_COUNT)) return true;
            if (c.equals(MEDIA_TYPE) || c.contains("." + MEDIA_TYPE)) return true;
            if (c.equals(GENRES) || c.contains("." + GENRES)) return true;
        }
        return false;
    }

}
