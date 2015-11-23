package com.niksplay.moviesland.adapter.item;

import com.niksplay.moviesland.model.IMedia;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemPagerMedias extends SimpleItem<List<? extends IMedia>> {

    public ItemPagerMedias(List<? extends IMedia> data) {
        super(data, ItemType.TYPE_MEDIAS);
    }
}
