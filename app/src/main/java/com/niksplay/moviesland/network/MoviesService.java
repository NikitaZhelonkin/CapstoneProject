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

    @GET("{type}/{id}/images")
    Call<ImagesResponse> getImages(@Path("type") String type, @Path("id") long id);

    @GET("{type}/{id}/credits")
    Call<CreditsResponse> getCredits(@Path("type") String type, @Path("id") long id);

    @GET("{type}/{id}/reviews")
    Call<PagedResponse<Review>> getReviews(@Path("type") String type, @Path("id") long id);

    @GET("movie/{id}/similar")
    Call<PagedResponse<Movie>> getMovieSimilar(@Path("id") long id);

    @GET("movie/now_playing")
    Call<PagedResponse<Movie>> moviesNowPlaying(@Query("page") int page);

    @GET("movie/popular")
    Call<PagedResponse<Movie>> moviesPopular(@Query("page") int page);

    @GET("movie/top_rated")
    Call<PagedResponse<Movie>> moviesTopRated(@Query("page") int page);

    @GET("movie/upcoming")
    Call<PagedResponse<Movie>> moviesUpcoming(@Query("page") int page);

    @GET("tv/{id}")
    Call<TV> getTV(@Path("id") long id);

    @GET("tv/{id}/similar")
    Call<PagedResponse<TV>> getTVSimilar(@Path("id") long id);

    @GET("tv/on_the_air")
    Call<PagedResponse<TV>> tvOnTheAir(@Query("page") int page);

    @GET("tv/airing_today")
    Call<PagedResponse<TV>> tvAiringToday(@Query("page") int page);

    @GET("tv/top_rated")
    Call<PagedResponse<TV>> tvTopRated(@Query("page") int page);

    @GET("tv/popular")
    Call<PagedResponse<TV>> tvPopular(@Query("page") int page);

    @GET("person/popular")
    Call<PagedResponse<Person>> personsPopular(@Query("page") int page);

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
