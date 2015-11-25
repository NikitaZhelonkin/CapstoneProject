package com.niksplay.moviesland.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.item.IListItem;
import com.niksplay.moviesland.adapter.item.ItemPersonHeader;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.utils.ImageUrls;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 23.11.15.
 */
public class PersonDetailHolder extends AbsViewHolder {

    @Bind(R.id.person_thumbnail)
    ImageView mImageView;
    @Bind(R.id.person_name)
    TextView mNameView;
    @Bind(R.id.person_date_of_birth)
    TextView mBirthDateView;
    @Bind(R.id.person_place_of_birth)
    TextView mBirthPlaceView;
    @Bind(R.id.person_description)
    TextView mBiographyView;

    public PersonDetailHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(IListItem iListItem) {
        if (iListItem instanceof ItemPersonHeader) {
            Person person = ((ItemPersonHeader) iListItem).getItemData();
            Context context = mNameView.getContext();
            mBiographyView.setText(person.biography);
            mNameView.setText(person.name);
            mBirthDateView.setText(person.birthday);
            mBirthPlaceView.setText(person.placeOfBirth);
            Picasso.with(context).load(ImageUrls.getPosterUrl(person.profilePath)).into(mImageView);
        }
    }
}
