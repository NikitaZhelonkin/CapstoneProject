package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemLabel;
import com.niksplay.moviesland.adapter.item.ItemMediaImages;
import com.niksplay.moviesland.adapter.item.ItemPagerPersons;
import com.niksplay.moviesland.adapter.item.ItemPagerMedias;
import com.niksplay.moviesland.adapter.item.ItemReview;
import com.niksplay.moviesland.adapter.item.MediaDetailHeaderItem;
import com.niksplay.moviesland.loader.MovieDetailInfoLoader;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.MediaDetailInfo;
import com.niksplay.moviesland.model.Review;
import com.niksplay.moviesland.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<MediaDetailInfo> {

    private static final String EXTRA_MEDIA = "extra_media";

    private IMedia mMedia;

    private MediaDetailInfo mMediaDetailInfo;

    private MediaDetailsAdapter mAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static MediaDetailFragment create(IMedia movie) {
        MediaDetailFragment mediaDetailFragment = new MediaDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MEDIA, movie);
        mediaDetailFragment.setArguments(bundle);
        return mediaDetailFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMedia = getArguments().getParcelable(EXTRA_MEDIA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_detail, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getActivity().setTitle(mMedia.getTitle());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new MediaDetailsAdapter());

        invalidate();
    }

    @Override
    public Loader<MediaDetailInfo> onCreateLoader(int id, Bundle args) {
        return new MovieDetailInfoLoader(getActivity(), mMedia);
    }

    @Override
    public void onLoadFinished(Loader<MediaDetailInfo> loader, MediaDetailInfo data) {
        mMediaDetailInfo = data;
        invalidate();
    }

    @Override
    public void onLoaderReset(Loader<MediaDetailInfo> loader) {
        //do nothing
    }

    private void invalidate() {
        List<IListItem> items = new ArrayList<>();
        items.add(new MediaDetailHeaderItem(mMedia));

        if (mMediaDetailInfo != null) {
            if (mMediaDetailInfo.images != null && !ArrayUtils.isEmpty(mMediaDetailInfo.images.backdrops)) {
                items.add(new ItemMediaImages(mMediaDetailInfo.images.backdrops));
            }

            if (mMediaDetailInfo.credits != null && !ArrayUtils.isEmpty(mMediaDetailInfo.credits.cast)) {
                items.add(new ItemLabel(getString(R.string.label_persons)));
                items.add(new ItemPagerPersons(mMediaDetailInfo.credits.cast));
            }

            if (!ArrayUtils.isEmpty(mMediaDetailInfo.relatedMedia)) {
                items.add(new ItemLabel(getString(R.string.label_similar)));
                items.add(new ItemPagerMedias(mMediaDetailInfo.relatedMedia));
            }

            if (!ArrayUtils.isEmpty(mMediaDetailInfo.reviews)) {
                for (Review review : mMediaDetailInfo.reviews) {
                    items.add(new ItemReview(review));
                }
            }
        }

        mAdapter.setData(items);
    }

}
