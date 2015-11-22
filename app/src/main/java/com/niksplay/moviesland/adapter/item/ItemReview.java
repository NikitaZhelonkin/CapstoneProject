package com.niksplay.moviesland.adapter.item;

import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.model.Review;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemReview extends SimpleItem<Review> {

    public ItemReview(Review data) {
        super(data, MediaDetailsAdapter.TYPE_REVIEW);
    }
}
