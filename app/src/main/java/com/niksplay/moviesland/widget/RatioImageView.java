package com.niksplay.moviesland.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.niksplay.moviesland.R;

/**
 * Created by nikita on 21.11.15.
 */
public class RatioImageView extends ImageView {

    public float mRatio = -1;

    public RatioImageView(Context context) {
        super(context);
        init(context, null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
            mRatio = a.getFloat(R.styleable.RatioImageView_ratio, -1);
            a.recycle();
        }
    }

    public void setRatio(float ratio) {
        if (ratio != mRatio) {
            mRatio = ratio;
            requestLayout();
        }
    }

    public float getRatio() {
        return mRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mRatio != -1) {
            if (heightMeasureSpec == MeasureSpec.EXACTLY) {
                int width = resolveSize((int) (getMeasuredHeight() * mRatio), widthMeasureSpec);
                int height = (int) (width / mRatio);
                setMeasuredDimension(width, height);
            } else {
                int height = resolveSize((int) (getMeasuredWidth() / mRatio), heightMeasureSpec);
                int width = (int) (height * mRatio);
                setMeasuredDimension(width, height);
            }

        }
    }
}
