package com.niksplay.moviesland.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.LabelHolder;
import com.niksplay.moviesland.adapter.holder.MediaDetailHeaderHolder;
import com.niksplay.moviesland.adapter.holder.MediaImagesHolder;
import com.niksplay.moviesland.adapter.holder.MediaPersonsHolder;
import com.niksplay.moviesland.adapter.holder.MediaReviewHolder;
import com.niksplay.moviesland.adapter.holder.MediaSimilarHolder;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailsAdapter extends AbsAdapter {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_IMAGES = 1;
    public static final int TYPE_LABEL = 2;
    public static final int TYPE_PERSONS = 3;
    public static final int TYPE_SIMILAR = 4;
    public static final int TYPE_REVIEW = 5;

    @Override
    public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_HEADER:
                return new MediaDetailHeaderHolder(inflater.inflate(R.layout.list_item_media_header, parent, false));
            case TYPE_IMAGES:
                return new MediaImagesHolder(inflater.inflate(R.layout.list_item_media_images, parent, false));
            case TYPE_LABEL:
                return new LabelHolder(inflater.inflate(R.layout.list_item_label, parent, false));
            case TYPE_PERSONS:
                return new MediaPersonsHolder(inflater.inflate(R.layout.list_item_media_persons, parent, false));
            case TYPE_SIMILAR:
                return new MediaSimilarHolder(inflater.inflate(R.layout.list_item_media_similar, parent, false));
            case TYPE_REVIEW:
                return new MediaReviewHolder(inflater.inflate(R.layout.list_item_review, parent, false));
            default:
                throw new IllegalArgumentException("Unsupported view type " + viewType);
        }
    }


}
