package com.niksplay.moviesland.model;

/**
 * Created by nikita on 16.11.15.
 */
public interface IMedia {

    String getBackdropPath();

    int[] getGenreIds();

    long getId();

    String getOriginalTitle();

    String getTitle();

    String getOverview();

    String getReleaseDate();

    String getPosterPath();

    float getPopularity();

    boolean isVideo();

    float getVoteAverage();

    int getVoteCount();

}
