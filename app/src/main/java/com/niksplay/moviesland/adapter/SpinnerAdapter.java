package com.niksplay.moviesland.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.niksplay.moviesland.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 18.11.15.
 */
public class SpinnerAdapter  extends BaseAdapter {
    private List<String> mItems = new ArrayList<>();

    public void clear() {
        mItems.clear();
    }

    public void addItem(String yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<String> yourObjectList) {
        mItems.addAll(yourObjectList);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar_spinner, parent, false);
        }
        TextView titleView = (TextView) view.findViewById(android.R.id.text1);
        titleView.setText(getTitle(position));
        return view;
    }

    public String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : "";
    }
}
