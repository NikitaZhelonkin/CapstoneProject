package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.MediaAdapter;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.MediasPagerHolder;
import com.niksplay.moviesland.model.IMedia;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemPagerMedias extends SimpleItem<List<? extends IMedia>> {

    public MediasPagerHolder.OnItemSelectedListener mItemSelectedListener;

    public ItemPagerMedias(List<? extends IMedia> data, MediasPagerHolder.OnItemSelectedListener l) {
        super(data, ItemType.TYPE_MEDIAS);
        mItemSelectedListener = l;
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MediasPagerHolder(inflater.inflate(R.layout.list_item_medias, parent, false), mItemSelectedListener);
    }
}
