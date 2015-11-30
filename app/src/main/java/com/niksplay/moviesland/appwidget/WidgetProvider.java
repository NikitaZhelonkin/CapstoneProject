package com.niksplay.moviesland.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RemoteViews;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.activity.MainActivity;
import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.utils.ArrayUtils;
import com.niksplay.moviesland.utils.Utils;

/**
 * Created by nikita on 29.11.15.
 */
public class WidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_MEDIA_ID = "media";
    public static final String ACTION_DATA_UPDATED = "com.niksplay.moviesland.appwidget.DATA_UPDATED";
    public static final String ACTION_PREVIOUS = "com.niksplay.moviesland.appwidget.ACTION_PREVIOUS";
    public static final String ACTION_NEXT = "com.niksplay.moviesland.appwidget.ACTION_NEXT";
    public static final String ACTION_ITEM_CLICK = "com.niksplay.moviesland.appwidget.ITEM_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        context.startService(new Intent(context, WidgetFetchService.class));
    }
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {
        context.startService(new Intent(context, WidgetFetchService.class));
    }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (Utils.isConnected()) {
                context.startService(new Intent(context, WidgetFetchService.class));
            }
        } else if (ACTION_DATA_UPDATED.equals(intent.getAction())) {
            updateAppWidget(context);
        } else if (ACTION_PREVIOUS.equals(intent.getAction())) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_app_widget);
            rv.showPrevious(R.id.view_flipper);

            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(appWidgetId, rv);

        } else if (ACTION_NEXT.equals(intent.getAction())) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_app_widget);
            rv.showNext(R.id.view_flipper);
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(appWidgetId, rv);
        } else if (ACTION_ITEM_CLICK.equals(intent.getAction())) {
            IMedia media = WidgetFetchService.getDataItem(intent.getLongExtra(EXTRA_MEDIA_ID, -1));
            if (media != null) {
                Intent mainActivityIntent = new Intent(context, MainActivity.class);
                mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent mediaActivityIntent = MediaDetailActivity.createIntent(context, media);
                context.startActivities(new Intent[]{mainActivityIntent, mediaActivityIntent});
            }
        }
    }

    private void updateAppWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        final int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context,
                WidgetProvider.class));

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_app_widget);

        boolean emptyData = ArrayUtils.isEmpty(WidgetFetchService.getData());

        for (int appWidgetId : appWidgetIds) {
            Intent serviceIntent = new Intent(context, FlipWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            // embed extras so they don't get ignored
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            remoteViews.setRemoteAdapter(R.id.view_flipper, serviceIntent);
            remoteViews.setEmptyView(R.id.view_flipper, R.id.empty_view);

            remoteViews.setViewVisibility(R.id.btn_next, emptyData ? View.GONE : View.VISIBLE);
            remoteViews.setViewVisibility(R.id.btn_previous, emptyData ? View.GONE : View.VISIBLE);

            final PendingIntent nextPendingIntent = selfPendingIntent(context, ACTION_NEXT, appWidgetId);
            remoteViews.setOnClickPendingIntent(R.id.btn_next, nextPendingIntent);

            final PendingIntent prevPendingIntent = selfPendingIntent(context, ACTION_PREVIOUS, appWidgetId);
            remoteViews.setOnClickPendingIntent(R.id.btn_previous, prevPendingIntent);

            Intent clickIntent = new Intent(context, WidgetProvider.class);
            clickIntent.setAction(WidgetProvider.ACTION_ITEM_CLICK);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            clickIntent.setData(Uri.parse(clickIntent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent clickPendingIntent = PendingIntent.getBroadcast(
                    context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.view_flipper, clickPendingIntent);

            // update widget
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.view_flipper);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private PendingIntent selfPendingIntent(Context context, String action, int appWidgetId) {
        final Intent selfIntent = new Intent(context, WidgetProvider.class);
        selfIntent.setAction(action);
        selfIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        selfIntent.setData(Uri.parse(selfIntent.toUri(Intent.URI_INTENT_SCHEME)));
        return PendingIntent.getBroadcast(context, 0, selfIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


}
