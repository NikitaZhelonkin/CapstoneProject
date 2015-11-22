package com.niksplay.moviesland.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.MediaDetailHeaderItem;
import com.niksplay.moviesland.fragment.MediaDetailFragment;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailHeaderHolder extends AbsViewHolder {

    @Bind(R.id.movie_title)
    TextView movieTitle;
    @Bind(R.id.movie_year)
    TextView yearView;
    @Bind(R.id.movie_rate)
    TextView ratingView;
    @Bind(R.id.movie_description)
    TextView descriptionView;
    @Bind(R.id.movie_thumbnail)
    ImageView movieImage;
    @Bind(R.id.movie_genres)
    TextView genresView;
    @Bind(R.id.favorite_btn)
    Button favoriteButton;
    @Bind(R.id.watchlist_btn)
    Button watchlistButton;


    Context mContext;

    public MediaDetailHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    @Override
    public void bind(IListItem iListItem) {
        if(iListItem instanceof MediaDetailHeaderItem){
            IMedia media =   ((MediaDetailHeaderItem) iListItem).getItemData();
            int year = Utils.getYear(media.getReleaseDate());
            movieTitle.setText(media.getTitle());
            yearView.setText(year == 0 ? "" : String.valueOf(year));
            genresView.setText(Utils.formatGenres(media));
            ratingView.setText(String.valueOf(media.getVoteAverage()));
            descriptionView.setText(media.getOverview());
            Picasso.with(mContext).load(ImageUrls.getPosterUrl(media.getPosterPath())).into(movieImage);

            favoriteButton.setText("Favorite");
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            watchlistButton.setText("Watchlist");
            watchlistButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
