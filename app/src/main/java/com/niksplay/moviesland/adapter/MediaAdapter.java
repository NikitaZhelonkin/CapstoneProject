package com.niksplay.moviesland.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 16.11.15.
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    public interface OnItemSelectedListener {
        void onItemSelected(IMedia media);
    }

    private List<IMedia> mData;

    private OnItemSelectedListener mOnItemSelectedListener;

    public void addAll(List<? extends IMedia> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<? extends IMedia> data) {
        mData = data == null ? null : new ArrayList<>(data);
        notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener l) {
        mOnItemSelectedListener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final IMedia media = mData.get(position);
        int year = Utils.getYear(media.getReleaseDate());
        holder.mTitleView.setText(media.getTitle());
        holder.mDescriptionView.setText(media.getOverview());
        holder.mRateView.setText(String.valueOf(media.getVoteAverage()));
        holder.mReleaseDate.setText(year == 0 ? "" : String.valueOf(year));
        holder.mGenresView.setText(Utils.formatGenres(media));
        Picasso.with(holder.mImageView.getContext()).load(ImageUrls.getPosterUrl(media.getPosterPath())).into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemSelectedListener != null) {
                    mOnItemSelectedListener.onItemSelected(media);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movie_title)
        TextView mTitleView;
        @Bind(R.id.movie_description)
        TextView mDescriptionView;
        @Bind(R.id.movie_thumbnail)
        ImageView mImageView;
        @Bind(R.id.movie_rate)
        TextView mRateView;
        @Bind(R.id.movie_release_year)
        TextView mReleaseDate;
        @Bind(R.id.movie_genres)
        TextView mGenresView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
