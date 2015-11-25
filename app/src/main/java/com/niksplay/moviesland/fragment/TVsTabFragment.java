package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.adapter.MediaAdapter;
import com.niksplay.moviesland.adapter.MediaSelectedListener;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.io.IOException;

import retrofit.Response;

/**
 * Created by nikita on 16.11.15.
 */
public class TVsTabFragment extends PagingListFragment<TV>{

    private static final String EXTRA_TAB = "tab";

    public static final int TAB_POPULAR = 0;
    public static final int TAB_TOP_RATED = 1;
    public static final int TAB_ON_THE_AIR = 2;
    public static final int TAB_AIRING_TODAY = 3;

    private MediaAdapter mAdapter;

    private int mTab;

    public static TVsTabFragment create(int tab) {
        TVsTabFragment fragment = new TVsTabFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter = new MediaAdapter());
        mAdapter.setOnItemSelectedListener(new MediaSelectedListener(getActivity()));
    }

    @Override
    public PagedResponse<TV> loadInBackground(int page) {
        try {
            Response<PagedResponse<TV>> response;
            switch (mTab) {
                default:
                case TAB_POPULAR:
                    response = App.getInstance().getApiClient().tvPopular(page).execute();
                    break;
                case TAB_TOP_RATED:
                    response = App.getInstance().getApiClient().tvTopRated(page).execute();
                    break;
                case TAB_ON_THE_AIR:
                    response = App.getInstance().getApiClient().tvOnTheAir(page).execute();
                    break;
                case TAB_AIRING_TODAY:
                    response = App.getInstance().getApiClient().tvAiringToday(page).execute();
                    break;
            }
            if (response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<PagedResponse<TV>> loader, PagedResponse<TV> data) {
        super.onLoadFinished(loader, data);
        if (data != null) {
            mAdapter.addAll(data.getResults());
        }
    }
}
