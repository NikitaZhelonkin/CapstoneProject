package com.niksplay.moviesland.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.niksplay.moviesland.adapter.item.IListItem;

/**
 * Created by nikita on 21.11.15.
 */
public abstract class AbsViewHolder extends RecyclerView.ViewHolder {

    public AbsViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(IListItem iListItem);
}

