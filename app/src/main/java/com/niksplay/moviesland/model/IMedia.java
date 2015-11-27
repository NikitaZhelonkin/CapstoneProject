package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.niksplay.moviesland.model.response.VideosResponse;

/**
 * Created by nikita on 16.11.15.
 */
public interface IMedia extends Parcelable {

    enum Type {
        MOVIE("movie"), TV("tv");

        private String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    String getBackdropPath();

    int[] getGenreIds();

    Genre[] getGenres();

    long getId();

    String getOriginalTitle();

    String getTitle();

    String getOverview();

    String getReleaseDate();

    String getPosterPath();

    String getHomePage();

    float getPopularity();

    boolean isVideo();

    float getVoteAverage();

    int getVoteCount();

    VideosResponse getVideos();

    Type getType();

    Creator<IMedia> CREATOR = new Creator<IMedia>() {
        @Override
        public IMedia createFromParcel(Parcel parcel) {
            String type = parcel.readString();
            if(Type.MOVIE.name.equals(type)){
                return Movie.CREATOR.createFromParcel(parcel);
            }else{
                return TV.CREATOR.createFromParcel(parcel);
            }
        }

        @Override
        public IMedia[] newArray(int i) {
            return new IMedia[i];
        }
    };

}
