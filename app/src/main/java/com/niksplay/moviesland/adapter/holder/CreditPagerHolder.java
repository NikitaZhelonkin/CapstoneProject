package com.niksplay.moviesland.adapter.holder;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemPagerCredits;
import com.niksplay.moviesland.model.Credit;
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
public class CreditPagerHolder extends AbsViewHolder {

    public interface OnItemSelectedListener{
        void onItemSelected(Credit credit);
    }

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private OnItemSelectedListener mOnItemSelectedListener;

    private int mCount;

    public CreditPagerHolder(View itemView, int count, OnItemSelectedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mViewPager.setAdapter(mAdapter = new ViewPagerAdapter());
        mOnItemSelectedListener = listener;
        mCount = count;
    }


    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemPagerCredits) {
            List<Credit> creditList = ((ItemPagerCredits) iListItem).getItemData();
            List<CreditBlock> blocks = new ArrayList<>();
            int blocksSize = creditList.size() / mCount + (creditList.size() % mCount == 0 ? 0 : 1);
            for (int i = 0; i < blocksSize; i += mCount) {
                Credit[] credits = new Credit[mCount];
                for (int j = 0; j < mCount && i + j < creditList.size(); j++) {
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
            DoublerView view = (DoublerView)inflater.inflate(R.layout.page_item_images, pager, false);
            Credit[] credits = mData.get(position).credits;
            for (int i = 0; i < mCount; i++) {
                ImageView imageView = (ImageView) view.getChildAt(i).findViewById(R.id.image_view);
                if (i < credits.length && credits[i] != null) {
                    imageView.setVisibility(View.VISIBLE);

                    Picasso.with(pager.getContext()).load(ImageUrls.getPosterUrl(credits[i].getImageUrl())).into(imageView);
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                }
                imageView.setTag(credits[i]);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemSelectedListener != null) {
                            mOnItemSelectedListener.onItemSelected((Credit) view.getTag());
                        }
                    }
                });
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
