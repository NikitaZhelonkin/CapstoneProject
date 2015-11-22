package com.niksplay.moviesland.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemLabel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 21.11.15.
 */
public class LabelHolder extends AbsViewHolder {

    @Bind(R.id.label_view)
    TextView mLabelView;

    public LabelHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IListItem iListItem) {
        if(iListItem instanceof ItemLabel){
            mLabelView.setText(((ItemLabel) iListItem).getItemData());
        }
    }
}
