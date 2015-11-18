package com.niksplay.moviesland.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.niksplay.moviesland.Constants;
import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.MoviesAdapter;
import com.niksplay.moviesland.adapter.SpinnerSubtitleAdapter;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.managers.Genres;
import com.niksplay.moviesland.model.Genre;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.model.response.PagedResponse;
import com.niksplay.moviesland.widget.EndlessRecyclerScrollListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;

/**
 * Created by nikita on 18.11.15.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PagedResponse<? extends IMedia>>{

    public enum CatalogType {
        MOVIE("movie"), TV("tv");

        private String name;

        CatalogType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static final String EXTRA_TYPE = "type";
    private  static final  String[] SORT_NAMES = {"By Popularity", "By Release date", "By Revenue", "By Vote average", "By Vote count"};
    private static final  String[] SORT_VALUES = {"popularity.desc", "release_date.desc", "revenue.desc", "vote_average.desc", "vote_count.desc"};

    @Bind(R.id.toolbar_spinner)
    Spinner mToolbarSpinner;
    @Bind(R.id.genre_spinner)
    Spinner mGenreSpinner;
    @Bind(R.id.year_spinner)
    Spinner mYearSpinner;
    @Bind(R.id.sort_spinner)
    Spinner mSortSpinner;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    private ArrayAdapter<String> mGenresAdapter;
    private ArrayAdapter<String> mYearAdapter;
    private MoviesAdapter mMediaAdapter;

    private CatalogType mType = CatalogType.MOVIE;

    private int mPage;
    private int mTotalPages;
    private boolean mLoading = false;

    public static Intent createIntent(Context context, CatalogType type){
        Intent i = new Intent(context, CatalogActivity.class);
        i.putExtra(EXTRA_TYPE, type);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        mType = (CatalogType) getIntent().getSerializableExtra(EXTRA_TYPE);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        SpinnerSubtitleAdapter adapter = new SpinnerSubtitleAdapter();
        adapter.setTitle(getString(R.string.title_catalog));
        adapter.addItem(getString(R.string.title_movies));
        adapter.addItem(getString(R.string.title_tvs));
        mToolbarSpinner.setAdapter(adapter);
        mToolbarSpinner.setSelection(mType != CatalogType.TV ? 0 : 1);
        mToolbarSpinner.setOnItemSelectedListener(new SimpleItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mType = i == 0 ? CatalogType.MOVIE : CatalogType.TV;
                mGenresAdapter.clear();
                mGenresAdapter.addAll(getGenresStringList());
                mGenresAdapter.notifyDataSetChanged();
                mGenreSpinner.setAdapter(mGenresAdapter);

                super.onItemSelected(adapterView, view, i, l);
            }

        });

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SORT_NAMES);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSortSpinner.setAdapter(sortAdapter);
        mSortSpinner.setOnItemSelectedListener(new SimpleItemSelectedListener());

        mYearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getYears(1900));
        mYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(mYearAdapter);
        mYearSpinner.setOnItemSelectedListener(new SimpleItemSelectedListener());


        mGenresAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getGenresStringList());
        mGenresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenreSpinner.setAdapter(mGenresAdapter);
        mGenreSpinner.setOnItemSelectedListener(new SimpleItemSelectedListener());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMediaAdapter = new MoviesAdapter());
        mRecyclerView.addOnScrollListener(new EndlessRecyclerScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                if (!mLoading && mPage + 1 <= mTotalPages) {
                    mLoading = true;
                    getSupportLoaderManager().getLoader(0).forceLoad();
                }
            }
        });
    }


    @Override
    public Loader<PagedResponse<? extends IMedia>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<PagedResponse<? extends IMedia>>(this) {
            @Override
            public PagedResponse<? extends IMedia> loadInBackground() {
                Log.e("RESTART", "loadinBackground");
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.PARAM_YEAR, Constants.PARAM_YEAR);
                params.put(Constants.PARAM_WITH_GENRES, args.getString(Constants.PARAM_WITH_GENRES));
                params.put(Constants.PARAM_YEAR, args.getString(Constants.PARAM_YEAR));
                params.put(Constants.PARAM_PAGE, String.valueOf(mPage + 1));
                try {
                    switch (mType) {
                        case MOVIE:
                            Response<PagedResponse<Movie>> movies = App.getInstance().getApiClient().discoverMovie(params).execute();
                            if (movies.isSuccess()) {
                                return movies.body();
                            }
                            break;
                        case TV:
                            Response<PagedResponse<TV>> tvs = App.getInstance().getApiClient().discoverTv(params).execute();
                            if (tvs.isSuccess()) {
                                return tvs.body();
                            }
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                mLoading = true;
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<PagedResponse<? extends IMedia>> loader, PagedResponse<? extends IMedia> data) {
        mLoading = false;
        mProgressBar.setVisibility(View.GONE);
        if (data != null) {
            mPage = data.getPage();
            mTotalPages = data.getTotal_pages();
            mMediaAdapter.addAll(data.getResults());
        }
    }

    @Override
    public void onLoaderReset(Loader<PagedResponse<? extends IMedia>> loader) {
    }

    private Bundle getBundleForLoader() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_SORT_BY, SORT_VALUES[mSortSpinner.getSelectedItemPosition()]);
        if (mGenreSpinner.getSelectedItemPosition() != 0) {
            Genre genre = getGenresList().get(mGenreSpinner.getSelectedItemPosition() + 1);
            bundle.putString(Constants.PARAM_WITH_GENRES, String.valueOf(genre.id));
        }
        if (mYearSpinner.getSelectedItemPosition() != 0) {
            bundle.putString(Constants.PARAM_YEAR, String.valueOf(mYearAdapter.getItem(mGenreSpinner.getSelectedItemPosition() + 1)));
        }
        return bundle;
    }

    private List<String> getGenresStringList() {
        List<Genre> genres = getGenresList();
        List<String> strings = new ArrayList<>();
        strings.add(getString(R.string.spinner_genres_default));
        if (genres == null) {
            return strings;
        }
        for (Genre genre : genres) {
            strings.add(genre.name);
        }
        return strings;
    }

    private List<Genre> getGenresList() {
        if (mType == CatalogType.MOVIE) {
            return Genres.getMoviesGenres();
        } else {
            return Genres.getTVGenres();
        }
    }

    private String[] getYears(int min) {
        int max = Calendar.getInstance().get(Calendar.YEAR);
        if (min >= max) {
            return new String[0];
        }
        String[] s = new String[max - min + 2];
        s[0] = getString(R.string.spinner_year_default);
        for (int i = max; i >= min; i--) {
            s[max - i + 1] = String.valueOf(i);
        }
        return s;
    }

    private class SimpleItemSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            mPage = 0;
            mLoading = true;
            mMediaAdapter.setData(null);
            mProgressBar.setVisibility(View.VISIBLE);
            getSupportLoaderManager().restartLoader(0, getBundleForLoader(), CatalogActivity.this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            //do nothing
        }
    }

}
