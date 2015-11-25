package com.niksplay.moviesland.fragment;

import android.database.ContentObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.adapter.MediaAdapter;
import com.niksplay.moviesland.adapter.MediaSelectedListener;
import com.niksplay.moviesland.managers.FavoriteManager;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.provider.SimpleObserver;
import com.niksplay.moviesland.provider.favorite.FavoriteColumns;

import java.util.List;

import butterknife.Bind;

/**
 * Created by nikita on 15.11.15.
 */
public class FavoriteFragment extends NavigationListFragment<IMedia> {

    private MediaAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getContentResolver().registerContentObserver(FavoriteColumns.CONTENT_URI, true, mContentObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getContentResolver().unregisterContentObserver(mContentObserver);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new MediaAdapter());
        mAdapter.setOnItemSelectedListener(new MediaSelectedListener(getActivity()));
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_favorite;
    }

    @Override
    protected List<IMedia> loadInBackground() {
        return FavoriteManager.getFavoriteMedia();
    }

    @Override
    public void onLoadFinished(Loader<List<IMedia>> loader, List<IMedia> data) {
        super.onLoadFinished(loader, data);
        mAdapter.setData(data);
    }

    private ContentObserver mContentObserver = new SimpleObserver() {
        @Override
        public void onChange() {
            getLoaderManager().getLoader(0).forceLoad();
        }
    };

}
