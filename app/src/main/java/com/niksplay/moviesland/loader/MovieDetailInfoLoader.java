package com.niksplay.moviesland.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.MediaDetailInfo;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.Review;
import com.niksplay.moviesland.model.TV;
import com.niksplay.moviesland.model.response.CreditsResponse;
import com.niksplay.moviesland.model.response.ImagesResponse;
import com.niksplay.moviesland.model.response.PagedResponse;
import com.niksplay.moviesland.network.MoviesService;

import java.io.IOException;

import retrofit.Response;

/**
 * Created by nikita on 21.11.15.
 */
public class MovieDetailInfoLoader extends AsyncTaskLoader<MediaDetailInfo> {

    private IMedia mMedia;

    public MovieDetailInfoLoader(Context context, IMedia media) {
        super(context);
        mMedia = media;
    }

    @Override
    public MediaDetailInfo loadInBackground() {
        MoviesService moviesService = App.getInstance().getApiClient();
        try {
            MediaDetailInfo detailInfo = new MediaDetailInfo();

            Response<ImagesResponse> imagesResponse = moviesService.getImages(mMedia.getType().toString(), mMedia.getId()).execute();
            if (imagesResponse.isSuccess()) {
                detailInfo.images = imagesResponse.body();
            }
            Response<CreditsResponse> creditsResponse = moviesService.getCredits(mMedia.getType().toString(), mMedia.getId()).execute();
            if (creditsResponse.isSuccess()) {
                detailInfo.credits = creditsResponse.body();
            }

            if (mMedia.getType() == IMedia.Type.MOVIE) {
                Response<PagedResponse<Movie>> movieResponse = moviesService.getMovieSimilar(mMedia.getId()).execute();
                if (movieResponse.isSuccess()) {
                    detailInfo.relatedMedia = movieResponse.body().getResults();
                }
            } else {
                Response<PagedResponse<TV>> tvResponse = moviesService.getTVSimilar(mMedia.getId()).execute();
                if (tvResponse.isSuccess()) {
                    detailInfo.relatedMedia = tvResponse.body().getResults();
                }
            }

            Response<PagedResponse<Review>> reviewsResponse = moviesService.getReviews(mMedia.getType().toString(), mMedia.getId()).execute();
            if(reviewsResponse.isSuccess()){
                detailInfo.reviews = reviewsResponse.body().getResults();
            }
            return detailInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
