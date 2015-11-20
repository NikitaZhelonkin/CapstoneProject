package com.niksplay.moviesland.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.fragment.MediaDetailFragment;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Movie;

/**
 * Created by nikita on 19.11.15.
 */
public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MEDIA = "extra_media";

    public static Intent createIntent(Context context, IMedia media){
        Intent i = new Intent(context, MovieDetailActivity.class);
        i.putExtra(EXTRA_MEDIA, media);
        return i;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            MediaDetailFragment fragment = MediaDetailFragment.create((Movie) getIntent().getExtras().getParcelable(EXTRA_MEDIA));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
