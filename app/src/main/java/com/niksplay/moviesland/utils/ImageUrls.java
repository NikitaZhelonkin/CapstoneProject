package com.niksplay.moviesland.utils;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.network.MoviesService;

/**
 * Created by nikita on 16.11.15.
 */
public class ImageUrls {

    public static String getPosterUrl(String path) {
        String size = App.getInstance().getString(R.string.poster_size);
        return MoviesService.IMAGES_URL + size + path;
    }

    public static String getMediaImageUrl(String path) {
        String size = App.getInstance().getString(R.string.media_image_size);
        return MoviesService.IMAGES_URL + size + path;
    }

    public static String getBackdropUrl(String path) {
        String size = App.getInstance().getString(R.string.backdrop_size);
        return MoviesService.IMAGES_URL + size + path;
    }
}
