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

    @Bind(R.id.author_view)
    TextView mAuthorView;
    @Bind(R.id.content_view)
    TextView mContentView;


    public MediaReviewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IListItem iListItem) {
        if(iListItem instanceof ItemReview){
            Review review = ((ItemReview) iListItem).getItemData();
            mAuthorView.setText(review.author);
            mContentView.setText(review.content);
        }
    }
}
