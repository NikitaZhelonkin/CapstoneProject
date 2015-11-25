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
import android.widget.ImageView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.activity.PersonActivity;
import com.niksplay.moviesland.adapter.RecyclerItemsAdapter;
import com.niksplay.moviesland.adapter.holder.CreditPagerHolder;
import com.niksplay.moviesland.adapter.holder.MediaDetailHeaderHolder;
import com.niksplay.moviesland.adapter.holder.MediasPagerHolder;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemLabel;
import com.niksplay.moviesland.adapter.item.ItemMediaImages;
import com.niksplay.moviesland.adapter.item.ItemPagerCredits;
import com.niksplay.moviesland.adapter.item.ItemPagerMedias;
import com.niksplay.moviesland.adapter.item.ItemReview;
import com.niksplay.moviesland.adapter.item.ItemMediaDetailHeader;
import com.niksplay.moviesland.loader.MediaDetailInfoLoader;
import com.niksplay.moviesland.managers.FavoriteManager;
import com.niksplay.moviesland.managers.WatchlistManager;
import com.niksplay.moviesland.model.Credit;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.MediaDetailInfo;
import com.niksplay.moviesland.model.Review;
import com.niksplay.moviesland.utils.ArrayUtils;
import com.niksplay.moviesland.utils.ImageUrls;
import com.squareup.picasso.Picasso;

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

    private RecyclerItemsAdapter mAdapter;

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
        mRecyclerView.setAdapter(mAdapter = new RecyclerItemsAdapter());

        invalidate();
    }

    @Override
    public Loader<MediaDetailInfo> onCreateLoader(int id, Bundle args) {
        return new MediaDetailInfoLoader(getActivity(), mMedia);
    }

    @Override
    public void onLoadFinished(Loader<MediaDetailInfo> loader, MediaDetailInfo data) {
        mMedia = data != null ? data.media : mMedia;
        mMediaDetailInfo = data;
        invalidate();
    }

    @Override
    public void onLoaderReset(Loader<MediaDetailInfo> loader) {
        //do nothing
    }

    private void invalidate() {
        updateBackdropImage();
        List<IListItem> items = new ArrayList<>();
        items.add(new ItemMediaDetailHeader(mMedia, mButtonsClickListener));

        int pagerItemsCount = getResources().getInteger(R.integer.pager_items_count);

        if (mMediaDetailInfo != null) {
            if (mMediaDetailInfo.images != null && !ArrayUtils.isEmpty(mMediaDetailInfo.images.backdrops)) {
                items.add(new ItemMediaImages(mMediaDetailInfo.images.backdrops));
            }

            if (mMediaDetailInfo.credits != null && !ArrayUtils.isEmpty(mMediaDetailInfo.credits.cast)) {
                items.add(new ItemLabel(getString(R.string.label_persons)));
                items.add(new ItemPagerCredits(mMediaDetailInfo.credits.cast, pagerItemsCount, mPersonSelectedListener));
            }

            if (!ArrayUtils.isEmpty(mMediaDetailInfo.relatedMedia)) {
                items.add(new ItemLabel(getString(R.string.label_similar)));
                items.add(new ItemPagerMedias(mMediaDetailInfo.relatedMedia, pagerItemsCount, mRelatedItemSelectedListener));
            }

            if (!ArrayUtils.isEmpty(mMediaDetailInfo.reviews)) {
                for (Review review : mMediaDetailInfo.reviews) {
                    items.add(new ItemReview(review));
                }
            }
        }

        mAdapter.setData(items);
    }

    private void updateBackdropImage(){
        ImageView backdropView = (ImageView)getActivity().findViewById(R.id.backdrop_view);
        Picasso.with(getActivity()).load(ImageUrls.getBackdropUrl(mMedia.getBackdropPath())).into(backdropView);
    }

    private MediasPagerHolder.OnItemSelectedListener mRelatedItemSelectedListener = new MediasPagerHolder.OnItemSelectedListener() {
        @Override
        public void onItemSelected(IMedia media) {
            startActivity(MediaDetailActivity.createIntent(getActivity(), media));
        }
    };

    private CreditPagerHolder.OnItemSelectedListener mPersonSelectedListener = new CreditPagerHolder.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Credit credit) {
            startActivity(PersonActivity.createIntent(getActivity(), credit.createPerson()));
        }
    };

    private MediaDetailHeaderHolder.OnButtonsClickListener mButtonsClickListener = new MediaDetailHeaderHolder.OnButtonsClickListener() {
        @Override
        public void onFavoriteClick() {
            FavoriteManager.toggleFavorite(mMedia);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onWatchlistClick() {
            WatchlistManager.toggleWatchlist(mMedia);
            mAdapter.notifyDataSetChanged();
        }
    };

}
