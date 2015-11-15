package com.niksplay.moviesland.model.response;

import com.google.gson.annotations.SerializedName;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.model.TV;

import java.util.List;

/**
 * Created by nikita on 15.11.15.
 */
public class FindResponse {

    @SerializedName("movie_results")
    List<Movie> movieResults;
    @SerializedName("tv_results")
    List<TV> tvResults;
    @SerializedName("person_results")
    List<Person> personResults;
}
