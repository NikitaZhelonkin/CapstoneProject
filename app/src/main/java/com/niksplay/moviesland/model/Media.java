package com.niksplay.moviesland.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.niksplay.moviesland.model.response.VideosResponse;

/**
 * Created by nikita on 27.11.15.
 */
public class Media implements IMedia {

    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("genre_ids")
    public int[] genreIds;
    public long id;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("original_name")
    public String originalName;
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("first_air_date")
    public String releaseAirDate;
    @SerializedName("poster_path")
    public String posterPath;
    public float popularity;
    public String title;
    @SerializedName("name")
    public String name;
    public boolean video;
    @SerializedName("vote_average")
    public float voteAverage;
    @SerializedName("vote_count")
    public int voteCount;
    @SerializedName("media_type")
    public String mediaType;


    public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel parcel) {
            return new Media(parcel);
        }

        @Override
        public Media[] newArray(int i) {
            return new Media[i];
        }
    };

    public Media() {

    }

    protected Media(Parcel parcel) {
        mediaType = parcel.readString();
        backdropPath = parcel.readString();
        genreIds = parcel.createIntArray();
        id = parcel.readLong();
        originalTitle = parcel.readString();
        originalName = parcel.readString();
        overview = parcel.readString();
        releaseDate = parcel.readString();
        releaseAirDate = parcel.readString();
        posterPath = parcel.readString();
        popularity = parcel.readFloat();
        title = parcel.readString();
        name = parcel.readString();
        video = parcel.readInt() == 1;
        voteAverage = parcel.readFloat();
        voteCount = parcel.readInt();
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getType() == null ? "" : getType().toString());
        parcel.writeString(backdropPath);
        parcel.writeIntArray(genreIds);
        parcel.writeLong(id);
        parcel.writeString(originalTitle);
        parcel.writeString(originalName);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(releaseAirDate);
        parcel.writeString(posterPath);
        parcel.writeFloat(popularity);
        parcel.writeString(title);
        parcel.writeString(name);
        parcel.writeInt(video ? 1 : 0);
        parcel.writeFloat(voteAverage);
        parcel.writeInt(voteCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public int[] getGenreIds() {
        return genreIds;
    }

    @Override
    public Genre[] getGenres() {
        return null;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getOriginalTitle() {
        return !TextUtils.isEmpty(originalTitle) ? originalTitle : originalName;
    }

    @Override
    public String getTitle() {
        return !TextUtils.isEmpty(title) ? title : name;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getReleaseDate() {
        return !TextUtils.isEmpty(releaseDate) ? releaseDate : releaseAirDate;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public float getPopularity() {
        return popularity;
    }

    @Override
    public boolean isVideo() {
        return video;
    }

    @Override
    public float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String getHomePage() {
        return null;
    }


    @Override
    public VideosResponse getVideos() {
        return null;
    }

    @Override
    public IMedia.Type getType() {
        if (Type.TV.toString().equals(mediaType)) {
            return Type.TV;
        } else if (Type.MOVIE.toString().equals(mediaType)) {
            return Type.MOVIE;
        } else {
            return null;
        }
    }
}
