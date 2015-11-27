package com.niksplay.moviesland.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;

import com.niksplay.moviesland.Constants;
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
public class MediaDetailInfoLoader extends AsyncTaskLoader<MediaDetailInfo> {

    private IMedia mMedia;

    public MediaDetailInfoLoader(Context context, IMedia media) {
        super(context);
        mMedia = media;
    }

    @Override
    public MediaDetailInfo loadInBackground() {
        MoviesService moviesService = App.getInstance().getApiClient();
        try {
            MediaDetailInfo detailInfo = new MediaDetailInfo();

            if (mMedia.getType() == IMedia.Type.MOVIE) {
                Response<Movie> movieResponse = moviesService.getMovie(mMedia.getId(), Constants.PARAM_VIDEOS).execute();
                if (movieResponse.isSuccess()) {
                    detailInfo.media = movieResponse.body();
                }
                Response<PagedResponse<Movie>> movieRelatedResponse = moviesService.getMovieSimilar(mMedia.getId()).execute();
                if (movieRelatedResponse.isSuccess()) {
                    detailInfo.relatedMedia = movieRelatedResponse.body().getResults();
                }
            } else {
                Response<TV>  tvResponse = moviesService.getTV(mMedia.getId(), Constants.PARAM_VIDEOS).execute();
                if(tvResponse.isSuccess()){
                    detailInfo.media = tvResponse.body();
                }
                Response<PagedResponse<TV>> tvRelatedResponse = moviesService.getTVSimilar(mMedia.getId()).execute();
                if (tvRelatedResponse.isSuccess()) {
                    detailInfo.relatedMedia = tvRelatedResponse.body().getResults();
                }
            }

            Response<ImagesResponse> imagesResponse = moviesService.getImages(mMedia.getType().toString(), mMedia.getId()).execute();
            if (imagesResponse.isSuccess()) {
                detailInfo.images = imagesResponse.body();
            }
            Response<CreditsResponse> creditsResponse = moviesService.getCredits(mMedia.getType().toString(), mMedia.getId()).execute();
            if (creditsResponse.isSuccess()) {
                detailInfo.credits = creditsResponse.body();
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
