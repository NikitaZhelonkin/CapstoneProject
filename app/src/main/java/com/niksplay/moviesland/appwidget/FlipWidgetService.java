package com.niksplay.moviesland.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by nikita on 29.11.15.
 */
public class FlipWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FlipRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class FlipRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context mContext;
        private int mWidgetId;
        List<Movie> mData;

        public FlipRemoteViewsFactory(Context context, Intent intent) {
            mData = WidgetFetchService.getData();
            mContext = context;
            mWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            mData = WidgetFetchService.getData();
        }

        @Override
        public void onDataSetChanged() {
            mData = WidgetFetchService.getData();
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public int getCount() {
            return mData != null ? mData.size() : 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            final RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.layout_app_widget_item);
            if (getCount() > 0) {
                final IMedia media = mData.get(position);
                int year = Utils.getYear(media.getReleaseDate());
                views.setTextViewText(R.id.movie_title, media.getTitle());
                views.setTextViewText(R.id.movie_rate, String.valueOf(media.getVoteAverage()));
                views.setTextViewText(R.id.movie_release_year, year == 0 ? "" : String.valueOf(year));
                views.setTextViewText(R.id.movie_genres, Utils.formatGenres(media));
                loadImage(views, R.id.movie_thumbnail, ImageUrls.getPosterUrl(media.getPosterPath()));
                loadImage(views, R.id.backdrop_image_view, ImageUrls.getBackdropUrl(media.getBackdropPath()));

                final Intent clickIntent = new Intent(mContext, WidgetProvider.class);
                clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mWidgetId);
                clickIntent.putExtra(WidgetProvider.EXTRA_MEDIA_ID, media.getId());
                views.setOnClickFillInIntent(R.id.backdrop_image_view, clickIntent);
            }
            return views;
        }

        private void loadImage(RemoteViews views, int viewId, String url){
            try {
                views.setImageViewBitmap(viewId, Picasso.with(mContext).load(url).get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
