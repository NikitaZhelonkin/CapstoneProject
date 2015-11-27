package com.niksplay.moviesland.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.fragment.MediaDetailFragment;
import com.niksplay.moviesland.model.IMedia;

/**
 * Created by nikita on 19.11.15.
 */
public class MediaDetailActivity extends AppCompatActivity {

    private static final String SHARE_TYPE = "text/plain";
    private static final String EXTRA_MEDIA = "extra_media";

    public static Intent createIntent(Context context, IMedia media){

        Intent i = new Intent(context, MediaDetailActivity.class);
        i.putExtra(EXTRA_MEDIA, media);
        return i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        IMedia media =  getIntent().getExtras().getParcelable(EXTRA_MEDIA);

        if (savedInstanceState == null) {
            MediaDetailFragment fragment = MediaDetailFragment.create(media);
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

    public void onShareClick(View view) {
        MediaDetailFragment detailFragment = (MediaDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (detailFragment != null) {
            IMedia media = detailFragment.getMedia();
            if (media != null && !TextUtils.isEmpty(media.getHomePage())) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, media.getHomePage());
                sendIntent.setType(SHARE_TYPE);
                startActivity(sendIntent);
            }
        }
    }

}
