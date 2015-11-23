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
public class MediaDetailHeaderItem extends SimpleItem<IMedia> {

    public MediaDetailHeaderItem(IMedia media) {
        super(media, ItemType.TYPE_MEDIA_HEADER);
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MediaDetailHeaderHolder(inflater.inflate(R.layout.list_item_media_header, parent, false));
    }
}
