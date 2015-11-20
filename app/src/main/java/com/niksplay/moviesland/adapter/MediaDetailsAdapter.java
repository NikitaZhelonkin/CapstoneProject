package com.niksplay.moviesland.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.MediaDetailHeaderHolder;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailsAdapter extends AbsAdapter{

    public static  final  int TYPE_HEADER = 0;

    @Override
    public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_HEADER:
                return new MediaDetailHeaderHolder(inflater.inflate(R.layout.list_item_media_header, parent, false));
            default:
                throw new IllegalArgumentException("Unsupported view type " + viewType);
        }
    }


}
