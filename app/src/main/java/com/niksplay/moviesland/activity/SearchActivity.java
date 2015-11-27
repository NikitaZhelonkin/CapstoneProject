package com.niksplay.moviesland.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.model.response.PagedResponse;
import com.niksplay.moviesland.utils.Utils;
import com.niksplay.moviesland.widget.EndlessRecyclerScrollListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 18.11.15.
 */
public abstract class SearchActivity<T> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PagedResponse<T>> {

    private static final String SAVED_STATE_QUERY = "query";

    private String mQuery;

    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.empty_view) View mEmptyView;

    private int mPage;
    private int mTotalPages;
    private boolean mLoading = false;

    private Bundle mSavedState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSavedState = savedInstanceState;

        ButterKnife.bind(this);

        getSupportLoaderManager().initLoader(0, null, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int columnsCount = getResources().getInteger(R.integer.column_count);
        GridLayoutManager layoutManager = new GridLayoutManager(this, columnsCount);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                if (!mLoading && mPage + 1 <= mTotalPages) {
                    mLoading = true;
                    getSupportLoaderManager().getLoader(0).forceLoad();
                }
            }
        });

        handleIntent(getIntent());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_STATE_QUERY, mQuery);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                menu.findItem(R.id.menu_search).collapseActionView();
                Intent i = getSearchIntent();
                i.putExtra(SearchManager.QUERY, s);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        if (TextUtils.isEmpty(mQuery)) {
            menu.findItem(R.id.menu_search).expandActionView();
        } else {
            searchView.clearFocus();
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            if (!item.getActionView().isFocused()) {
                (item.getActionView()).requestFocus();
                EditText editText = (EditText) item.getActionView().findViewById(android.support.v7.appcompat.R.id.search_src_text);
                Utils.showKeyboard(editText);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<PagedResponse<T>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<PagedResponse<T>>(SearchActivity.this) {
            @Override
            public PagedResponse<T> loadInBackground() {
                return searchInBackground(mQuery, mPage + 1);
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
        };
    }



    @Override
    public void onLoadFinished(Loader<PagedResponse<T>> loader, PagedResponse< T> data) {
        mLoading = false;
        mProgressBar.setVisibility(View.GONE);
        if (data != null) {
            mPage = data.getPage();
            mTotalPages = data.getTotal_pages();
        }
    }

    @Override
    public void onLoaderReset(Loader<PagedResponse<T>> loader) {
        //do nothing
    }

    public String getQuery(){
        return mQuery;
    }

    protected abstract PagedResponse<T> searchInBackground(String query, int page);

    protected abstract Intent getSearchIntent();

    private void handleIntent(Intent intent) {
        mQuery = intent.getStringExtra(SearchManager.QUERY);
        if (mSavedState != null) {
            mQuery = mSavedState.getString(SAVED_STATE_QUERY);
            mSavedState = null;
        }

        if (!TextUtils.isEmpty(mQuery)) {
            setTitle(mQuery);
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mPage = 0;
        mLoading = true;
        getSupportLoaderManager().restartLoader(0, null, this);
    }

}
