package com.niksplay.moviesland.managers;

import android.util.Log;

import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.Genre;
import com.niksplay.moviesland.model.response.GenresResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by nikita on 16.11.15.
 */
public class Genres {

    private static List<Genre> sMovieGenres;
    private static List<Genre> sTVGenres;

    public static void loadGenres() {
        App.getInstance().getApiClient().genresList("movie").enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Response<GenresResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    sMovieGenres = response.body().genres;
                }else{
                    Log.e("TAG", "TAG:"+response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TAG", "TAG:"+t);
            }
        });

        App.getInstance().getApiClient().genresList("tv").enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Response<GenresResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    sTVGenres = response.body().genres;
                }else{
                    Log.e("TAG", "TAG:"+response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TAG", "TAG:"+t);
            }
        });
    }

    public static Genre getGenre(long id) {
        Genre genre = findGenre(id, sMovieGenres);
        if (genre != null) {
            return genre;
        }
        return findGenre(id, sTVGenres);
    }

    public static List<Genre> getMoviesGenres(){
        return sMovieGenres;
    }

    public static List<Genre> getTVGenres(){
        return sTVGenres;
    }

    private static Genre findGenre(long id, List<Genre> genres) {
        if (genres == null) {
            return null;
        }
        for (Genre genre : genres) {
            if (genre.id == id) {
                return genre;
            }
        }
        return null;
    }


}
