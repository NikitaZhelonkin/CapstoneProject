package com.niksplay.moviesland.appwidget;

import android.app.IntentService;
import android.content.Intent;

import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

/**
 * Created by nikita on 29.11.15.
 */
public class WidgetFetchService extends IntentService {

    public WidgetFetchService() {
        super(WidgetFetchService.class.getSimpleName());
    }

    private static List<Movie> sData;

    @Override
    protected void onHandleIntent(Intent intent) {
        final Response<PagedResponse<Movie>> response = getMedias();
        if (response != null && response.isSuccess()) {
            sData = response.body().getResults();
        }
        Intent widgetUpdateIntent = new Intent();
        widgetUpdateIntent.setAction(WidgetProvider.ACTION_DATA_UPDATED);
        sendBroadcast(widgetUpdateIntent);
    }


    private Response<PagedResponse<Movie>> getMedias() {
        try {
            return App.getInstance().getApiClient().moviesPopular(1).execute();
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Movie> getData() {
        return sData != null ? new ArrayList<>(sData) : null;
    }

    public static IMedia getDataItem(long id) {
        if (sData == null) {
            return null;
        }
        for (IMedia media : sData) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }


}
