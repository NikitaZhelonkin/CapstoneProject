package com.niksplay.moviesland.model.response;

import com.niksplay.moviesland.model.Video;

import java.util.List;

/**
 * Created by nikita on 27.11.15.
 */
public class VideosResponse {

    public List<Video> results;

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
