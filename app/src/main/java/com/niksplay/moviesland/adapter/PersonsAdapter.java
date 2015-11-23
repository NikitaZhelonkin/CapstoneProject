package com.niksplay.moviesland.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.model.Person;
import com.niksplay.moviesland.utils.ImageUrls;
import com.niksplay.moviesland.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 17.11.15.
 */
public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    public interface OnItemSelectedListener{
        void onItemSelected(Person person);
    }

    private List<Person> mData;

    private OnItemSelectedListener mItemSelectedListener;

    public void addAll(List<Person> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setItemSelectedListener(OnItemSelectedListener l){
        mItemSelectedListener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.mNameView.getContext();
        final Person person = mData.get(position);
        holder.mBiographyView.setText(person.biography);
        holder.mNameView.setText(person.name);
        String knownFor = Utils.formatKnownFor(person);
        holder.mKnowForView.setText(!TextUtils.isEmpty(knownFor) ? context.getString(R.string.known_for, knownFor) : "");
        Picasso.with(context).load(ImageUrls.getPersonPosterUrl(person.profilePath)).into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemSelectedListener!=null){
                    mItemSelectedListener.onItemSelected(person);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.person_thumbnail)
        ImageView mImageView;
        @Bind(R.id.person_name)
        TextView mNameView;
        @Bind(R.id.person_description)
        TextView mBiographyView;
        @Bind(R.id.person_known_for)
        TextView mKnowForView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
