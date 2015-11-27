package com.niksplay.moviesland.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;

import com.niksplay.moviesland.adapter.MediaAdapter;
import com.niksplay.moviesland.adapter.PersonSelectedListener;
import com.niksplay.moviesland.adapter.PersonsAdapter;
import com.niksplay.moviesland.app.App;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.model.response.PagedResponse;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

/**
 * Created by nikita on 27.11.15.
 */
public class PersonSearchActivity extends SearchActivity<Person> {

    private PersonsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setAdapter(mAdapter = new PersonsAdapter());
        mAdapter.setItemSelectedListener(new PersonSelectedListener(this));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mAdapter.clear();
    }

    @Override
    protected PagedResponse<Person> searchInBackground(String query, int page) {
        try {
            Response<PagedResponse<Person>> response = App.getInstance().getApiClient().searchPerson(query, page).execute();
            if(response.isSuccess()){
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<PagedResponse<Person>> loader, PagedResponse<Person> data) {
        super.onLoadFinished(loader, data);
        if (data != null) {
            mAdapter.addAll(data.getResults());
        }
        mEmptyView.setVisibility(!TextUtils.isEmpty(getQuery()) && mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }


    @Override
    protected Intent getSearchIntent() {
        return new Intent(this, PersonSearchActivity.class);
    }
}
