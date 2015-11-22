package com.niksplay.moviesland.adapter.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemMediaImages;
import com.niksplay.moviesland.model.Image;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.widget.RatioImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaImagesHolder extends AbsViewHolder {

    @Bind(R.id.recycler_view_images)
    RecyclerView mRecyclerView;

    Adapter mAdapter;

    public MediaImagesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter = new Adapter());
    }

    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemMediaImages) {
            mAdapter.setData(((ItemMediaImages) iListItem).getItemData());
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Image> mData;

        public Adapter() {
        }

        public void setData(List<Image> images) {
            mData = images;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ViewHolder(inflater.inflate(R.layout.list_item_media_image, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData != null ? mData.size() : 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_view)
        RatioImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Image image) {
            mImageView.setRatio(image.aspectRatio);
            Picasso.with(mImageView.getContext()).load(ImageUrls.getMediaImageUrl(image.filePath)).into(mImageView);
        }

    }
}
