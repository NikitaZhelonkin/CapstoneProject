package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.niksplay.moviesland.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 24.11.15.
 */
public abstract class NavigationListFragment<T> extends NavigationFragment implements LoaderManager.LoaderCallbacks<List<T>> {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public Loader<List<T>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<T>>(getActivity()) {
            @Override
            public List<T> loadInBackground() {
                return NavigationListFragment.this.loadInBackground();
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<T>> loader, List<T> data) {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<T>> loader) {
        //do nothing
    }

    protected abstract List<T> loadInBackground();
}
