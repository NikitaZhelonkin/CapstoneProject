package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.MediaDetailHeaderItem;
import com.niksplay.moviesland.model.IMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailFragment extends Fragment {

    private static final String EXTRA_MEDIA = "extra_media";

    private IMedia mMedia;

    private MediaDetailsAdapter mAdapter;


    public static MediaDetailFragment create(IMedia movie) {
        MediaDetailFragment mediaDetailFragment = new MediaDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MEDIA, movie);
        mediaDetailFragment.setArguments(bundle);
        return mediaDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mMedia = getArguments().getParcelable(EXTRA_MEDIA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter = new MediaDetailsAdapter());
        invalidate();
    }

    private void invalidate() {
        List<IListItem> items = new ArrayList<>();
        items.add(new MediaDetailHeaderItem(mMedia));
        mAdapter.setData(items);
    }
}
