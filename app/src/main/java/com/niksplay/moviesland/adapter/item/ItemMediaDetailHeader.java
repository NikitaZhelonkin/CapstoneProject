package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.MediaDetailHeaderHolder;
import com.niksplay.moviesland.model.IMedia;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemMediaDetailHeader extends SimpleItem<IMedia> {

    private MediaDetailHeaderHolder.OnButtonsClickListener mOnButtonsClickListener;

    public ItemMediaDetailHeader(IMedia media, MediaDetailHeaderHolder.OnButtonsClickListener listener) {
        super(media, ItemType.TYPE_MEDIA_HEADER);
        mOnButtonsClickListener = listener;
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MediaDetailHeaderHolder(inflater.inflate(R.layout.list_item_media_header, parent, false), mOnButtonsClickListener);
    }
}
