package com.niksplay.moviesland.adapter.holder;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemPagerMedias;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.widget.DoublerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediasPagerHolder extends AbsViewHolder {

    public interface  OnItemSelectedListener{
        void onItemSelected(IMedia media);
    }

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private OnItemSelectedListener mOnItemSelectedListener;
    private int mCount;

    public MediasPagerHolder(View itemView, int count, OnItemSelectedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mCount = count;
        mOnItemSelectedListener = listener;
        mViewPager.setAdapter(mAdapter = new ViewPagerAdapter());
    }


    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemPagerMedias) {
            List<? extends  IMedia> mediaList = ((ItemPagerMedias) iListItem).getItemData();
            List<MediaBlock> blocks = new ArrayList<>();
            int blocksSize = mediaList.size() / mCount + (mediaList.size() % mCount == 0 ? 0 : 1);
            for (int i = 0; i < blocksSize; i += mCount) {
                IMedia[] media = new IMedia[mCount];
                for (int j = 0; j < mCount && i + j < mediaList.size(); j++) {
                    media[j] = mediaList.get(i + j);
                }
                blocks.add(new MediaBlock(media));
            }
            mAdapter.setData(blocks);
        }
    }


    private class ViewPagerAdapter extends com.niksplay.moviesland.adapter.ViewPagerAdapter {

        private List<MediaBlock> mData;

        public void setData(List<MediaBlock> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mData != null ? mData.size() : 0;
        }


        @Override
        public View getView(int position, ViewPager pager) {
            LayoutInflater inflater = LayoutInflater.from(pager.getContext());
            DoublerView view = (DoublerView)inflater.inflate(R.layout.page_item_images, pager, false);
            IMedia[] medias = mData.get(position).medias;
            for (int i = 0; i < mCount; i++) {
                ImageView imageView = (ImageView) view.getChildAt(i).findViewById(R.id.image_view);
                if (i < medias.length && medias[i] != null) {
                    imageView.setVisibility(View.VISIBLE);
                    Picasso.with(pager.getContext()).load(ImageUrls.getPosterUrl(medias[i].getPosterPath())).into(imageView);
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                }
                imageView.setTag(medias[i]);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemSelectedListener != null) {
                            mOnItemSelectedListener.onItemSelected((IMedia) view.getTag());
                        }
                    }
                });
            }
            return view;
        }

    }


    private class MediaBlock {
        IMedia[] medias;

        public MediaBlock(IMedia[] medias) {
            this.medias = medias;
        }
    }

}

