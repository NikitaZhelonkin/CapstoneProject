package com.niksplay.moviesland.adapter.holder;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemPagerPersons;
import com.niksplay.moviesland.model.Credit;
import com.niksplay.moviesland.utils.ImageUrls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class PersonsPagerHolder extends AbsViewHolder {

    private static final int COUNT = 4;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    public PersonsPagerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mViewPager.setAdapter(mAdapter = new ViewPagerAdapter());
    }


    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemPagerPersons) {
            List<Credit> creditList = ((ItemPagerPersons) iListItem).getItemData();
            List<CreditBlock> blocks = new ArrayList<>();
            int blocksSize = creditList.size() / COUNT + (creditList.size() % COUNT == 0 ? 0 : 1);
            for (int i = 0; i < blocksSize; i += COUNT) {
                Credit[] credits = new Credit[COUNT];
                for (int j = 0; j < COUNT && i + j < creditList.size(); j++) {
                    credits[j] = creditList.get(i + j);
                }
                blocks.add(new CreditBlock(credits));
            }
            mAdapter.setData(blocks);
        }
    }


    private class ViewPagerAdapter extends com.niksplay.moviesland.adapter.ViewPagerAdapter {

        private List<CreditBlock> mData;

        public void setData(List<CreditBlock> data) {
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
            Credit[] credits = mData.get(position).credits;
            for (int i = 0; i < COUNT; i++) {
                if (i < credits.length && credits[i] != null) {
                    imageViews[i].setVisibility(View.VISIBLE);
                    Picasso.with(pager.getContext()).load(ImageUrls.getPersonPosterUrl(credits[i].profilePath)).into(imageViews[i]);
                } else {
                    imageViews[i].setVisibility(View.INVISIBLE);
                }
            }
            return view;
        }

    }


    private class CreditBlock {
        Credit[] credits;

        public CreditBlock(Credit[] credits) {
            this.credits = credits;
        }
    }

}
