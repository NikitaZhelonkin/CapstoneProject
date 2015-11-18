package com.niksplay.moviesland.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niksplay.moviesland.R;

/**
 * Created by nikita on 18.11.15.
 */
public class SpinnerSubtitleAdapter extends SpinnerAdapter {

    public String mTitle;

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar_subtitle_spinner, parent, false);
        }
        TextView titleView = (TextView) view.findViewById(android.R.id.text1);
        titleView.setText(mTitle);
        TextView subtitleView = (TextView) view.findViewById(android.R.id.text2);
        subtitleView.setText(getTitle(position));
        return view;
    }
}
