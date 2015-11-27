package com.niksplay.moviesland.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.model.Review;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 27.11.15.
 */
public class ReviewActivity extends AppCompatActivity {

    private static final String EXTRA_REVIEW = "review";

    @Bind(R.id.author_view)
    TextView mAuthorView;
    @Bind(R.id.content_view)
    TextView mContentView;

    public static Intent createIntent(Context context, Review review){
        Intent i = new Intent(context, ReviewActivity.class);
        i.putExtra(EXTRA_REVIEW, review);
        return i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_review);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Review review = getIntent().getParcelableExtra(EXTRA_REVIEW);
        mAuthorView.setText(review.author);
        mContentView.setText(review.content);
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
