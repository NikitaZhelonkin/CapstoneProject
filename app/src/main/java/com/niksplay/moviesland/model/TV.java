package com.niksplay.moviesland.model;

import android.os.Parcel;

import com.niksplay.moviesland.provider.favorite.FavoriteCursor;
import com.niksplay.moviesland.provider.watchlist.WatchlistCursor;

/**
 * Created by nikita on 15.11.15.
 */
public class TV extends Movie {


    public static final Creator<TV> CREATOR = new Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel parcel) {
            return new TV(parcel);
        }

        @Override
        public TV[] newArray(int i) {
            return new TV[i];
        }
    };


    @Override
    public Type getType() {
        return Type.TV;
    }

    public TV(){

    }

    public TV(FavoriteCursor cursor){
        super(cursor);
    }

    public TV(WatchlistCursor cursor){
        super(cursor);
    }

    protected TV(Parcel parcel){
        super(parcel);
    }

}
