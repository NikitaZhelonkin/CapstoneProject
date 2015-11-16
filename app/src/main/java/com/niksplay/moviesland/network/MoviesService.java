package com.niksplay.moviesland.network;

import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.model.Review;
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.model.response.CreditsResponse;
import com.niksplay.moviesland.model.response.FindResponse;
import com.niksplay.moviesland.model.response.GenresResponse;
import com.niksplay.moviesland.model.response.ImagesResponse;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by nikita on 15.11.15.
 */
public interface MoviesService {

    String API_KEY = "cee3889329734d105d1af14dfe7e17a4";

    String API_URL = "http://api.themoviedb.org/3/";
    String IMAGES_URL = "http://image.tmdb.org/t/p/";

    @GET("discover/movie")
    Call<PagedResponse<Movie>> discoverMovie(@QueryMap Map<String, String> params);

    @GET("discover/tv")
    Call<PagedResponse<TV>> discoverTv(@QueryMap Map<String, String> params);

    @GET("find/{id}")
    Call<FindResponse> find(@Path("id") long id);

    @GET("genre/{type}/list")
    Call<GenresResponse> genresList(@Path("type") String type);

    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") long id);

    @GET("movie/{id}/images")
    Call<ImagesResponse> getMovieImages(@Path("id") long id);

    @GET("movie/{id}/similar")
    Call<PagedResponse<Movie>> getMovieSimilar(@Path("id") long id);

    @GET("movie/{id}/reviews")
    Call<PagedResponse<Review>> getMovieReviews(@Path("id") long id);

    @GET("movie/now_playing")
    Call<PagedResponse<Movie>> moviesNowPlaying();

    @GET("movie/popular")
    Call<PagedResponse<Movie>> moviesPopular();

    @GET("movie/top_rated")
    Call<PagedResponse<Movie>> moviesTopRated();

    @GET("movie/upcoming")
    Call<PagedResponse<Movie>> moviesUpcoming();

    @GET("tv/{id}")
    Call<TV> getTV(@Path("id") long id);

    @GET("tv/{id}/images")
    Call<ImagesResponse> getTVImages(@Path("id") long id);

    @GET("tv/{id}/similar")
    Call<PagedResponse<TV>> getTVSimilar(@Path("id") long id);

    @GET("tv/on_the_air")
    Call<PagedResponse<TV>> tvOnTheAir();

    @GET("tv/airing_today")
    Call<PagedResponse<TV>> tvAiringToday();

    @GET("tv/top_rated")
    Call<PagedResponse<TV>> tvTopRated();

    @GET("tv/popular")
    Call<PagedResponse<TV>> tvPopular();

    @GET("person/{id}")
    Call<Person> getPerson(@Path("id") long id);

    @GET("person/{id}/combined_credits")
    Call<CreditsResponse> personCredits(@Path("id") long id);

    @GET("search/movie")
    Call<PagedResponse<Movie>> searchMovie(@Query("query") String query, @Query("page") int page);

    @GET("search/tv")
    Call<PagedResponse<TV>> searchTV(@Query("query") String query, @Query("page") int page);

    @GET("search/person")
    Call<PagedResponse<Person>> searchPerson(@Query("query") String query, @Query("page") int page);


}
