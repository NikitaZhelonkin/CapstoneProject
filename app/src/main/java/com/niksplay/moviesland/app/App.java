package com.niksplay.moviesland.app;

import android.app.Application;

import com.niksplay.moviesland.Constants;
import com.niksplay.moviesland.network.MoviesService;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
    }

    private void initMoviesApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .url(new HttpUrl.Builder()
                                .host(original.url().toString())
                                .addEncodedQueryParameter(Constants.PARAM_API_KEY, MoviesService.API_KEY).build())
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MoviesService.API_URL)
                .client(okHttpClient)
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
