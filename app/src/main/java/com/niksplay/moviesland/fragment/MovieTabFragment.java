package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.View;

import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.adapter.MediaAdapter;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.io.IOException;

import retrofit.Response;

/**
 * Created by nikita on 16.11.15.
 */
public class MovieTabFragment extends PagingListFragment<Movie> implements MediaAdapter.OnItemSelectedListener {

    private static final String EXTRA_TAB= "tab";

    public static final int TAB_POPULAR = 0;
    public static final int TAB_TOP_RATED = 1;
    public static final int TAB_UPCOMING = 2;
    public static final int TAB_NOW_PLAYING = 3;

    private MediaAdapter mAdapter;

    private int mTab;

    public static MovieTabFragment create(int tab) {
        MovieTabFragment fragment = new MovieTabFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_TAB, tab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab = getArguments().getInt(EXTRA_TAB);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter = new MediaAdapter());
        mAdapter.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(IMedia media) {
        startActivity(MediaDetailActivity.createIntent(getActivity(), media));
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
