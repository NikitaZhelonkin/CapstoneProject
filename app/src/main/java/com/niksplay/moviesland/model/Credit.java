package com.niksplay.moviesland.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 15.11.15.
 */
public class Credit {
    public long id;
    @SerializedName("credit_id")
    public String creditId;
    public String character;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("profile_path")
    public String profilePath;

    public String name;
}
