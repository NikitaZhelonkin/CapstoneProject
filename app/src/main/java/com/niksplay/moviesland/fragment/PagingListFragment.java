package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.model.response.PagedResponse;
import com.niksplay.moviesland.widget.EndlessRecyclerScrollListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 17.11.15.
 */
public abstract class PagingListFragment<T> extends Fragment implements LoaderManager.LoaderCallbacks<PagedResponse<T>> {

    private static final String EXTRA_PAGE = "page";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    private int mPage;

    private int mTotalPages;

    private boolean mLoading = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPage = 0;
        getLoaderManager().restartLoader(0, bundleForPage(mPage + 1), this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        int columnCount = getResources().getInteger(R.integer.column_count);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), columnCount);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                if (!mLoading && mPage + 1 <= mTotalPages) {
                    getLoaderManager().restartLoader(0, bundleForPage(mPage + 1), PagingListFragment.this);
                }
            }
        });

    }

    @Override
    public final Loader<PagedResponse<T>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<PagedResponse<T>>(getActivity()) {
            @Override
            public PagedResponse<T> loadInBackground() {
                return PagingListFragment.this.loadInBackground(args.getInt(EXTRA_PAGE));
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                mLoading = true;
                forceLoad();
            }
        };
    }

    public abstract PagedResponse<T> loadInBackground(int page);

    @Override
    public void onLoadFinished(Loader<PagedResponse<T>> loader, PagedResponse<T> data) {
        mLoading = false;
        mProgressBar.setVisibility(View.GONE);
        if (data != null) {
            mTotalPages = data.getTotal_pages();
            mPage = data.getPage();
        }
    }

    @Override
    public void onLoaderReset(Loader<PagedResponse<T>> loader) {

    }

    private Bundle bundleForPage(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_PAGE, page);
        return bundle;
    }
}
