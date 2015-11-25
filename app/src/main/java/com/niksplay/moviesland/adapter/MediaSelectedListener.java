package com.niksplay.moviesland.adapter;

import android.app.Activity;

import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.model.IMedia;

/**
 * Created by nikita on 25.11.15.
 */
public class MediaSelectedListener implements MediaAdapter.OnItemSelectedListener {

    private Activity activity;

    public MediaSelectedListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onItemSelected(IMedia media) {
        activity.startActivity(MediaDetailActivity.createIntent(activity, media));
    }
}
