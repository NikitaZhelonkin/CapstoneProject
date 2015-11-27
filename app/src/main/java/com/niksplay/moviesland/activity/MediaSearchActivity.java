package com.niksplay.moviesland.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;

import com.niksplay.moviesland.adapter.MediaAdapter;
import com.niksplay.moviesland.adapter.MediaSelectedListener;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Media;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

/**
 * Created by nikita on 26.11.15.
 */
public class MediaSearchActivity extends SearchActivity<Media> {

    private MediaAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setAdapter(mAdapter = new MediaAdapter());
        mAdapter.setOnItemSelectedListener(new MediaSelectedListener(this));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mAdapter.setData(null);
    }

    @Override
    protected PagedResponse<Media> searchInBackground(String query, int page) {
        try {
            Response<PagedResponse<Media>> responseMedia = App.getInstance().getApiClient().search(query, page).execute();
            if (responseMedia.isSuccess()) {
                return responseMedia.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<PagedResponse<Media>> loader, PagedResponse<Media> data) {
        super.onLoadFinished(loader, data);
        if (data != null) {
            mAdapter.addAll(filterByAvailableMediaType(data.getResults()));
        }
        mEmptyView.setVisibility(!TextUtils.isEmpty(getQuery()) && mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    protected Intent getSearchIntent() {
        return new Intent(this, MediaSearchActivity.class);
    }

    private List<Media> filterByAvailableMediaType(List<Media> medias) {
        if (medias == null) {
            return null;
        }
        List<Media> result = new ArrayList<>();
        for (Media media : medias) {
            if (media.getType() != null) {
                result.add(media);
            }
        }
        return result;
    }
}
