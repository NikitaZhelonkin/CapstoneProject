package com.niksplay.moviesland.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.niksplay.moviesland.activity.MainActivity;

/**
 * Created by nikita on 15.11.15.
 */
public abstract class NavigationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getTitleResId());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTabEnabled(false);
    }

    public void setTitle(CharSequence title){
        getActivity().setTitle(title);
    }

    public void setTitle(int titleResId){
        getActivity().setTitle(titleResId);
    }

    protected abstract int getTitleResId();

    public MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }

    protected TabLayout getTabLayout(){
        return getMainActivity().getTabLayout();
    }

    protected void setTabEnabled(boolean enabled) {
        getTabLayout().removeAllTabs();
        getTabLayout().setVisibility(enabled ? View.VISIBLE : View.GONE);
    }


}
