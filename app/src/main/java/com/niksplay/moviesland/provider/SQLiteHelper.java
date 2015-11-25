package com.niksplay.moviesland.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.niksplay.moviesland.BuildConfig;
import com.niksplay.moviesland.provider.favorite.FavoriteColumns;
import com.niksplay.moviesland.provider.watchlist.WatchlistColumns;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteHelper sInstance;
    private final Context mContext;
    private final SQLiteHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_FAVORITE = "CREATE TABLE IF NOT EXISTS "
            + FavoriteColumns.TABLE_NAME + " ( "
            + FavoriteColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FavoriteColumns.BACKDROP_PATH + " TEXT, "
            + FavoriteColumns.MOVIE_ID + " INTEGER, "
            + FavoriteColumns.ORIGINAL_TITLE + " TEXT, "
            + FavoriteColumns.OVERVIEW + " TEXT, "
            + FavoriteColumns.RELEASE_DATE + " TEXT, "
            + FavoriteColumns.POSTER_PATH + " TEXT, "
            + FavoriteColumns.POPULARITY + " REAL, "
            + FavoriteColumns.TITLE + " TEXT, "
            + FavoriteColumns.VOTE_AVERAGE + " REAL, "
            + FavoriteColumns.VOTE_COUNT + " INTEGER, "
            + FavoriteColumns.MEDIA_TYPE + " TEXT, "
            + FavoriteColumns.GENRES + " TEXT "
            + " );";

    public static final String SQL_CREATE_INDEX_FAVORITE_MOVIE_ID = "CREATE INDEX IDX_FAVORITE_MOVIE_ID "
            + " ON " + FavoriteColumns.TABLE_NAME + " ( " + FavoriteColumns.MOVIE_ID + " );";

    public static final String SQL_CREATE_TABLE_WATCHLIST = "CREATE TABLE IF NOT EXISTS "
            + WatchlistColumns.TABLE_NAME + " ( "
            + WatchlistColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WatchlistColumns.BACKDROP_PATH + " TEXT, "
            + WatchlistColumns.MOVIE_ID + " INTEGER, "
            + WatchlistColumns.ORIGINAL_TITLE + " TEXT, "
            + WatchlistColumns.OVERVIEW + " TEXT, "
            + WatchlistColumns.RELEASE_DATE + " TEXT, "
            + WatchlistColumns.POSTER_PATH + " TEXT, "
            + WatchlistColumns.POPULARITY + " REAL, "
            + WatchlistColumns.TITLE + " TEXT, "
            + WatchlistColumns.VOTE_AVERAGE + " REAL, "
            + WatchlistColumns.VOTE_COUNT + " INTEGER, "
            + WatchlistColumns.MEDIA_TYPE + " TEXT, "
            + WatchlistColumns.GENRES + " TEXT "
            + " );";

    public static final String SQL_CREATE_INDEX_WATCHLIST_MOVIE_ID = "CREATE INDEX IDX_WATCHLIST_MOVIE_ID "
            + " ON " + WatchlistColumns.TABLE_NAME + " ( " + WatchlistColumns.MOVIE_ID + " );";

    // @formatter:on

    public static SQLiteHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static SQLiteHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static SQLiteHelper newInstancePreHoneycomb(Context context) {
        return new SQLiteHelper(context);
    }

    private SQLiteHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new SQLiteHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static SQLiteHelper newInstancePostHoneycomb(Context context) {
        return new SQLiteHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private SQLiteHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new SQLiteHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_FAVORITE);
        db.execSQL(SQL_CREATE_INDEX_FAVORITE_MOVIE_ID);
        db.execSQL(SQL_CREATE_TABLE_WATCHLIST);
        db.execSQL(SQL_CREATE_INDEX_WATCHLIST_MOVIE_ID);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
