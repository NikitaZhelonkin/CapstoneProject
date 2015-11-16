package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;

/**
 * Created by nikita on 15.11.15.
 */
public class FavoriteFragment extends NavigationFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_favorite;
    }
}
