package com.niksplay.moviesland.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.LabelHolder;
import com.niksplay.moviesland.adapter.holder.MediasPagerHolder;
import com.niksplay.moviesland.adapter.holder.PersonDetailHolder;
import com.niksplay.moviesland.adapter.holder.SimpleHolder;
import com.niksplay.moviesland.adapter.item.ItemType;

/**
 * Created by nikita on 23.11.15.
 */
public class PersonDetailAdapter extends AbsAdapter {

    @Override
    public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ItemType.TYPE_PERSON_HEADER:
                return new PersonDetailHolder(inflater.inflate(R.layout.list_item_person_header, parent, false));
            case ItemType.TYPE_LOADER:
                return new SimpleHolder(inflater.inflate(R.layout.list_item_loader, parent, false));
            case ItemType.TYPE_LABEL:
                return new LabelHolder(inflater.inflate(R.layout.list_item_label, parent, false));
            case ItemType.TYPE_MEDIAS:
                return new MediasPagerHolder(inflater.inflate(R.layout.list_item_medias, parent, false));
            default:
                throw new IllegalArgumentException("Unsupported view type " + viewType);
        }
    }

}
