package com.niksplay.moviesland.adapter.holder;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemMediaSimilar;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.utils.ImageUrls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaSimilarHolder extends AbsViewHolder {

    private static final int COUNT = 4;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    public MediaSimilarHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mViewPager.setAdapter(mAdapter = new ViewPagerAdapter());
    }


    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemMediaSimilar) {
            List<? extends  IMedia> mediaList = ((ItemMediaSimilar) iListItem).getItemData();
            List<MediaBlock> blocks = new ArrayList<>();
            int blocksSize = mediaList.size() / COUNT + (mediaList.size() % COUNT == 0 ? 0 : 1);
            for (int i = 0; i < blocksSize; i += COUNT) {
                IMedia[] media = new IMedia[COUNT];
                for (int j = 0; j < COUNT && i + j < mediaList.size(); j++) {
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
            View view = inflater.inflate(R.layout.page_item_images, pager, false);
            ImageView[] imageViews = new ImageView[COUNT];
            imageViews[0] = (ImageView) view.findViewById(R.id.image1);
            imageViews[1] = (ImageView) view.findViewById(R.id.image2);
            imageViews[2] = (ImageView) view.findViewById(R.id.image3);
            imageViews[3] = (ImageView) view.findViewById(R.id.image4);
            IMedia[] medias = mData.get(position).medias;
            for (int i = 0; i < COUNT; i++) {
                if (i < medias.length && medias[i] != null) {
                    imageViews[i].setVisibility(View.VISIBLE);
                    Picasso.with(pager.getContext()).load(ImageUrls.getPosterUrl(medias[i].getPosterPath())).into(imageViews[i]);
                } else {
                    imageViews[i].setVisibility(View.INVISIBLE);
                }
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

