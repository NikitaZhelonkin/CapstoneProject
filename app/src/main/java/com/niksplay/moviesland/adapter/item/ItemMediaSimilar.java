package com.niksplay.moviesland.adapter.item;

import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.model.IMedia;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemMediaSimilar extends SimpleItem<List<? extends IMedia>> {

    public ItemMediaSimilar(List<? extends IMedia> data) {
        super(data, MediaDetailsAdapter.TYPE_SIMILAR);
    }
}
