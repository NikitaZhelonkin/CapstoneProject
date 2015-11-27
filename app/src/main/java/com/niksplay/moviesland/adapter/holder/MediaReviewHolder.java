package com.niksplay.moviesland.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemReview;
import com.niksplay.moviesland.model.Review;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class MediaReviewHolder extends AbsViewHolder {

    public interface OnItemSelectedListener {
        void onItemSelectedClick(Review review);
    }

    @Bind(R.id.author_view)
    TextView mAuthorView;
    @Bind(R.id.content_view)
    TextView mContentView;

    private OnItemSelectedListener mOnItemSelectedListener;

    public MediaReviewHolder(View itemView, OnItemSelectedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mOnItemSelectedListener = listener;
    }

    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemReview) {
            final Review review = ((ItemReview) iListItem).getItemData();
            mAuthorView.setText(review.author);
            mContentView.setText(review.content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemSelectedListener != null) {
                        mOnItemSelectedListener.onItemSelectedClick(review);
                    }
                }
            });
        }
    }
}
