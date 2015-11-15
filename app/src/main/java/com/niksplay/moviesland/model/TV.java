package com.niksplay.moviesland.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 15.11.15.
 */
public class TV extends Movie {
    @SerializedName("first_air_date")
    public String releaseDate;
    @SerializedName("original_name")
    public String originalTitle;
    @SerializedName("name")
    public String title;
}
