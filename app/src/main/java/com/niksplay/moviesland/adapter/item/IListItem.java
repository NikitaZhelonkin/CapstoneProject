package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.adapter.holder.AbsViewHolder;

/**
 * Created by nikita on 21.11.15.
 */
public interface IListItem {

    int getType();

    AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);
}
