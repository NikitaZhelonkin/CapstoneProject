package com.niksplay.moviesland.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.item.IListItem;

import java.util.List;

/**
 * Created by nikita on 21.11.15.
 */
public class RecyclerItemsAdapter extends RecyclerView.Adapter<AbsViewHolder> {

    private List<IListItem> mData;

    public void setData(List<IListItem> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public AbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        IListItem item = getItemOfType(viewType);
        if (item != null) {
            return item.onCreateViewHolder(inflater, parent);
        } else {
            throw new IllegalArgumentException("Unsupported view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(AbsViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    private IListItem getItemOfType(int type) {
        if (mData == null) {
            return null;
        }
        for (IListItem item : mData) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

}
