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
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;

/**
 * Created by nikita on 16.11.15.
 */
public class TVsTabFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<TV>>{

    private static final String EXTRA_TAB= "tab";

    public static final int TAB_POPULAR = 0;
    public static final int TAB_TOP_RATED = 1;
    public static final int TAB_ON_THE_AIR = 2;
    public static final int TAB_AIRING_TODAY = 3;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    private MoviesAdapter mAdapter;

    private int mTab;

    public static TVsTabFragment create(int tab) {
        TVsTabFragment fragment = new TVsTabFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_TAB, tab);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(1, null, this);
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new MoviesAdapter());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab = getArguments().getInt(EXTRA_TAB);
    }


    @Override
    public Loader<List<TV>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<TV>>(getActivity()) {
            @Override
            public List<TV> loadInBackground() {
                try {
                    Response<PagedResponse<TV>> response;
                    switch (mTab){
                        default:
                        case TAB_POPULAR:
                            response =  App.getInstance().getApiClient().tvPopular().execute();
                            break;
                        case TAB_TOP_RATED:
                            response =  App.getInstance().getApiClient().tvTopRated().execute();
                            break;
                        case TAB_ON_THE_AIR:
                            response =  App.getInstance().getApiClient().tvOnTheAir().execute();
                            break;
                        case TAB_AIRING_TODAY:
                            response =  App.getInstance().getApiClient().tvAiringToday().execute();
                            break;
                    }
                    if(response.isSuccess()){
                        return response.body().getResults();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<TV>> loader, List<TV> data) {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<TV>> loader) {

    }
}
