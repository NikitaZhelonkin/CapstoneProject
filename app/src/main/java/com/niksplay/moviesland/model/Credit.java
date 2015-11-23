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
    @SerializedName("media_type")
    public String mediaType;

    public String getImageUrl(){
        if (IMedia.Type.MOVIE.toString().equals(mediaType)) {
            return posterPath;
        }else if(IMedia.Type.TV.toString().equals(mediaType)){
            return posterPath;
        }else{
            return profilePath;
        }
    }

    public Person createPerson() {
        Person person = new Person();
        person.id = id;
        person.name = name;
        person.profilePath = profilePath;
        return person;
    }

    public IMedia createMedia() {
        if (mediaType.equals(IMedia.Type.MOVIE.toString())) {
            Movie movie = new Movie();
            movie.id = id;
            movie.title = originalTitle;
            movie.posterPath = posterPath;
            return movie;
        } else {
            TV tv = new TV();
            tv.id = id;
            tv.title = originalTitle;
            tv.posterPath = posterPath;
            return tv;
        }
    }
}
