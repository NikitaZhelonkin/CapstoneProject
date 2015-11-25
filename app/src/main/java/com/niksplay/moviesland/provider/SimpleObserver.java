package com.niksplay.moviesland.provider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

public abstract class SimpleObserver extends ContentObserver {

    public SimpleObserver() {
        super(new Handler(Looper.getMainLooper()));
    }

    @Override
    public final void onChange(boolean selfChange) {
        super.onChange(selfChange);
        onChange();
    }

    @Override
    public final void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        onChange();
    }

    public abstract void onChange();
}
