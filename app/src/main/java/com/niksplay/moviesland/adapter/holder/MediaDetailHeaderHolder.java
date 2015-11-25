package com.niksplay.moviesland.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemMediaDetailHeader;
import com.niksplay.moviesland.managers.FavoriteManager;
import com.niksplay.moviesland.managers.WatchlistManager;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaDetailHeaderHolder extends AbsViewHolder{

    public interface OnButtonsClickListener{
        void onFavoriteClick();
        void onWatchlistClick();
    }

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

    private OnButtonsClickListener mButtonsClickListener;

    public MediaDetailHeaderHolder(View itemView, OnButtonsClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mButtonsClickListener = listener;
    }

    @Override
    public void bind(IListItem iListItem) {
        if(iListItem instanceof ItemMediaDetailHeader){
            IMedia media =   ((ItemMediaDetailHeader) iListItem).getItemData();
            int year = Utils.getYear(media.getReleaseDate());
            movieTitle.setText(media.getTitle());
            yearView.setText(year == 0 ? "" : String.valueOf(year));
            genresView.setText(Utils.formatGenres(media));
            ratingView.setText(String.valueOf(media.getVoteAverage()));

            yearView.setVisibility(year == 0 ? View.INVISIBLE : View.VISIBLE);
            ratingView.setVisibility(media.getVoteAverage() == 0 ? View.INVISIBLE : View.VISIBLE);

            descriptionView.setText(media.getOverview());
            Picasso.with(mContext).load(ImageUrls.getPosterUrl(media.getPosterPath())).into(movieImage);

            favoriteButton.setText(FavoriteManager.isFavorite(media) ? R.string.from_favorite : R.string.to_favorite);
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mButtonsClickListener != null) {
                        mButtonsClickListener.onFavoriteClick();
                    }
                }
            });
            watchlistButton.setText(WatchlistManager.isInWatchlist(media) ? R.string.from_watchlist : R.string.to_watchlist);
            watchlistButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mButtonsClickListener != null) {
                        mButtonsClickListener.onWatchlistClick();
                    }
                }
            });

        }
    }
}
