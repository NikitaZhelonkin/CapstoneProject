package com.niksplay.moviesland.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nikita on 17.11.15.
 */
public abstract class EndlessRecyclerScrollListener extends RecyclerView.OnScrollListener {

    private static  final int VISIBLE_THRESHOLD = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager linearLayoutManager;

    public EndlessRecyclerScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if ((totalItemCount - visibleItemCount)
                <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            // End has been reached
            onLoadMore();
        }
    }

    public abstract void onLoadMore();


}
