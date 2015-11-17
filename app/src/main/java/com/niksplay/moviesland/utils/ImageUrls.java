package com.niksplay.moviesland.utils;

import com.niksplay.moviesland.network.MoviesService;

/**
 * Created by nikita on 16.11.15.
 */
public class ImageUrls {

    public static String getPosterUrl(String path){
        return MoviesService.IMAGES_URL + "w185" + path;
    }

    public static String getPersonPosterUrl(String path){
        return MoviesService.IMAGES_URL + "w185" + path;
    }
}
