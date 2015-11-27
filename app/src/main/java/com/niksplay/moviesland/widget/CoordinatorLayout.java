package com.niksplay.moviesland.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.utils.Utils;

/**
 * Created by nikita on 26.11.15.
 */
public class CoordinatorLayout extends DrawInsetsFrameLayout {

    private ColorDrawable mStatusBarColorDrawable;
    private int mStatusBarColor;
    private int mTopInset;
    private int mStatusBarFullOpacityBottom;
    private int mToolbarHeight;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private int mScrollY;


    public CoordinatorLayout(Context context) {
        super(context);
        init(context);
    }

    public CoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CoordinatorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    private void init(Context context) {
        setOnInsetsCallback(new DrawInsetsFrameLayout.OnInsetsCallback() {
            @Override
            public void onInsetsChanged(Rect insets) {
                mTopInset = insets.top;
            }
        });

        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            mToolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        mStatusBarFullOpacityBottom = getResources().getDimensionPixelSize(R.dimen.detail_card_top_margin);
        mStatusBarColorDrawable = new ColorDrawable(0);
        mStatusBarColor = getResources().getColor(R.color.colorPrimaryDark);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setupViews(toolbar, recyclerView);
    }

    public void setupViews(Toolbar toolbar, RecyclerView recyclerView) {
        mToolbar = toolbar;
        mRecyclerView = recyclerView;
        if (mToolbar != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    view.onApplyWindowInsets(windowInsets);
                    mTopInset = windowInsets.getSystemWindowInsetTop();
                    updateStatusBar();
                    updateToolbarPosition();
                    return windowInsets;
                }
            });
        }
        if (mRecyclerView != null && mToolbar != null) {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mScrollY = recyclerView.computeVerticalScrollOffset();
                    updateStatusBar();
                    updateToolbarPosition();
                }
            });
            mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                    updateStatusBar();
                    updateToolbarPosition();
                    return false;
                }
            });

        }
    }

    private void updateStatusBar() {
        int color = 0;
        View backdropView = mRecyclerView.findViewById(R.id.backdrop_view);
        if (backdropView != null && mTopInset != 0 && mScrollY > 0) {
            float f = Utils.progress(mScrollY,
                    mStatusBarFullOpacityBottom - mTopInset * 3,
                    mStatusBarFullOpacityBottom - mTopInset);
            color = Color.argb((int) (255 * f),
                    (Color.red(mStatusBarColor)),
                    (Color.green(mStatusBarColor)),
                    (Color.blue(mStatusBarColor)));
        } else if (mScrollY > 0) {
            color = mStatusBarColor;
        }
        mStatusBarColorDrawable.setColor(color);
        setInsetBackground(mStatusBarColorDrawable);
    }

    private void updateToolbarPosition() {
        if (mRecyclerView == null) {
            return;
        }
        View backdropView = mRecyclerView.findViewById(R.id.backdrop_view);
        if (backdropView != null) {
            float trans = mStatusBarFullOpacityBottom - mToolbarHeight - mScrollY;
            mToolbar.setTranslationY(Math.min(trans, mTopInset));
        }else{
            mToolbar.setTranslationY(-mToolbarHeight);
        }
    }

}
