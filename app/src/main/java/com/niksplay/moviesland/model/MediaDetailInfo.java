package com.niksplay.moviesland.model;

import com.niksplay.moviesland.model.response.CreditsResponse;
import com.niksplay.moviesland.model.response.ImagesResponse;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailInfo {


    public IMedia media;
    public ImagesResponse images;
    public List<Review> reviews;
    public List<? extends IMedia> relatedMedia;
    public CreditsResponse credits;
}
