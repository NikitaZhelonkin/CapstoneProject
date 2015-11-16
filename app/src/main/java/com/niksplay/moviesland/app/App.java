package com.niksplay.moviesland.app;

import android.app.Application;
import android.util.Log;

import com.niksplay.moviesland.Constants;
import com.niksplay.moviesland.managers.Genres;
import com.niksplay.moviesland.network.MoviesService;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by nikita on 15.11.15.
 */
public class App extends Application {

    private static App sInstance;


    private MoviesService mMoviesApi;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        initMoviesApiClient();

        Genres.loadGenres();
    }

    private void initMoviesApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                HttpUrl url = original.httpUrl()
                        .newBuilder()
                        .addQueryParameter(Constants.PARAM_API_KEY, MoviesService.API_KEY)
                        .build();

                Request request = original.newBuilder()
                        .url(url)
                        .method(original.method(), original.body())
                        .build();

                Log.e("TAG>>>",url.toString());
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MoviesService.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMoviesApi = retrofit.create(MoviesService.class);
    }

    public static App getInstance() {
        return sInstance;
    }

    public MoviesService getApiClient(){
        return mMoviesApi;
    }
}
