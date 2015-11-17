package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.MoviesAdapter;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.response.PagedResponse;
import com.niksplay.moviesland.widget.EndlessRecyclerScrollListener;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;

/**
 * Created by nikita on 16.11.15.
 */
public class MovieTabFragment extends PagingListFragment<Movie> {

    private static final String EXTRA_TAB= "tab";

    public static final int TAB_POPULAR = 0;
    public static final int TAB_TOP_RATED = 1;
    public static final int TAB_UPCOMING = 2;
    public static final int TAB_NOW_PLAYING = 3;

    private MoviesAdapter mAdapter;

    private int mTab;

    public static MovieTabFragment create(int tab) {
        MovieTabFragment fragment = new MovieTabFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_TAB, tab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab = getArguments().getInt(EXTRA_TAB);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter = new MoviesAdapter());
    }

    @Override
    public PagedResponse<Movie> loadInBackground(int page) {
        try {
            Response<PagedResponse<Movie>> response;
            switch (mTab){
                default:
                case TAB_POPULAR:
                    response =  App.getInstance().getApiClient().moviesPopular(page).execute();
                    break;
                case TAB_TOP_RATED:
                    response =  App.getInstance().getApiClient().moviesTopRated(page).execute();
                    break;
                case TAB_UPCOMING:
                    response =  App.getInstance().getApiClient().moviesUpcoming(page).execute();
                    break;
                case TAB_NOW_PLAYING:
                    response =  App.getInstance().getApiClient().moviesNowPlaying(page).execute();
                    break;
            }
            if(response.isSuccess()){
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<PagedResponse<Movie>> loader, PagedResponse<Movie> data) {
        super.onLoadFinished(loader, data);
        if (data != null) {
            mAdapter.addAll(data.getResults());
        }
    }

    @Override
    public void onLoaderReset(Loader<PagedResponse<Movie>> loader) {
        //do nothing
    }
}
