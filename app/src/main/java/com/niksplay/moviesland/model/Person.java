package com.niksplay.moviesland.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikita on 15.11.15.
 */
public class Person {

    public long id;
    public String biography;
    public String birthday;
    public String name;
    @SerializedName("place_of_birth")
    public String placeOfBirth;
    @SerializedName("profile_path")
    public String profilePath;
    @SerializedName("known_for")
    public List<Movie> knownFor;
}
