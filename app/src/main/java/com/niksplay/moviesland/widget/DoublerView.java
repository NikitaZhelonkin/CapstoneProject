package com.niksplay.moviesland.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.niksplay.moviesland.R;

/**
 * Created by nikita on 25.11.15.
 */
public class DoublerView extends LinearLayout {

    public DoublerView(Context context, int layoutId, int count) {
        super(context);
        init(context, layoutId, count);
    }


    public DoublerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DoublerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DoublerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs){
        if(attrs==null){
            throw new IllegalArgumentException("No layoutId, count attrs provided");
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DoublerView);
        int count = a.getInteger(R.styleable.DoublerView_count, -1);
        int layoutId = a.getResourceId(R.styleable.DoublerView_layoutId, -1);
        a.recycle();

        init(context, layoutId, count);

    }

    private void init(Context context, int layoutId, int count){
        if (layoutId == -1 || count <= 0) {
            throw new IllegalArgumentException("No layoutId or count attr provided");
        }
        setOrientation(HORIZONTAL);
        setWeightSum(count);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < count; i++) {
            View v = layoutInflater.inflate(layoutId, this, false);
            MarginLayoutParams marginsParams = ((MarginLayoutParams) v.getLayoutParams());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
            params.leftMargin = marginsParams.leftMargin;
            params.rightMargin = marginsParams.rightMargin;
            params.topMargin = marginsParams.topMargin;
            params.bottomMargin = marginsParams.bottomMargin;
            addView(v, params);
        }
    }

}
