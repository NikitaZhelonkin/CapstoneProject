package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.MediaReviewHolder;
import com.niksplay.moviesland.adapter.holder.MediasPagerHolder;
import com.niksplay.moviesland.model.Review;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemReview extends SimpleItem<Review> {

    private MediaReviewHolder.OnItemSelectedListener mOnItemSelectedListener;

    public ItemReview(Review data, MediaReviewHolder.OnItemSelectedListener listener) {
        super(data, ItemType.TYPE_REVIEW);
        mOnItemSelectedListener = listener;
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MediaReviewHolder(inflater.inflate(R.layout.list_item_review, parent, false), mOnItemSelectedListener);
    }
}
