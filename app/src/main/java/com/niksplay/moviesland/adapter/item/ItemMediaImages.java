package com.niksplay.moviesland.adapter.item;

import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.model.Image;
import com.niksplay.moviesland.model.response.ImagesResponse;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemMediaImages extends SimpleItem<List<Image>> {

    public ItemMediaImages(List<Image> data) {
        super(data, MediaDetailsAdapter.TYPE_IMAGES);
    }
}
