package com.niksplay.moviesland.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 15.11.15.
 */
public class Image {

    @SerializedName("file_path")
    public String filePath;
    @SerializedName("aspect_ratio")
    public float aspectRatio;

}
