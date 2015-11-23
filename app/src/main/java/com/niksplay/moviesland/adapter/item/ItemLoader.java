package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.SimpleHolder;

/**
 * Created by nikita on 23.11.15.
 */
public class ItemLoader implements IListItem {

    @Override
    public int getType() {
        return ItemType.TYPE_LOADER;
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new SimpleHolder(inflater.inflate(R.layout.list_item_loader, parent, false));
    }
}
