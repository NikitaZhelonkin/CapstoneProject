package com.niksplay.moviesland.model;

/**
 * Created by nikita on 27.11.15.
 */
public class Video {
    public String key;
    public String type;
    public String site;
    public String name;

    public String getUrl() {
        return "http://www.youtube.com/watch?v=" + key;
    }
}
