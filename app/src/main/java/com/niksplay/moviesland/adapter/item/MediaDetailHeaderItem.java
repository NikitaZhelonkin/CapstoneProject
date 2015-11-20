package com.niksplay.moviesland.adapter.item;

import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.model.IMedia;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailHeaderItem extends SimpleItem<IMedia> {

    public MediaDetailHeaderItem(IMedia media) {
        super(media, MediaDetailsAdapter.TYPE_HEADER);
    }
}
