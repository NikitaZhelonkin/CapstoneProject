package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.LabelHolder;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemLabel extends SimpleItem<String> {

    public ItemLabel(String data) {
        super(data, ItemType.TYPE_LABEL);
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new LabelHolder(inflater.inflate(R.layout.list_item_label, parent, false));
    }
}
