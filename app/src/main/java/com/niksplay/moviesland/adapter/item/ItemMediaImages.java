package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.MediaImagesHolder;
import com.niksplay.moviesland.adapter.holder.MediasPagerHolder;
import com.niksplay.moviesland.model.Image;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemMediaImages extends SimpleItem<List<Image>> {

    public ItemMediaImages(List<Image> data) {
        super(data, ItemType.TYPE_MEDIA_IMAGES);
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MediaImagesHolder(inflater.inflate(R.layout.list_item_media_images, parent, false));
    }
}
