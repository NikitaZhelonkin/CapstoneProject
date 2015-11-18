package com.niksplay.moviesland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.activity.CatalogActivity;
import com.niksplay.moviesland.activity.SearchActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nikita on 15.11.15.
 */
public class TVsFragment extends NavigationFragment {

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addTab(getString(R.string.tab_popular));
        adapter.addTab(getString(R.string.tab_top_rated));
        adapter.addTab(getString(R.string.tab_on_the_air));
        adapter.addTab(getString(R.string.tab_airing_today));

        mViewPager.setAdapter(adapter);

        setTabEnabled(true);
        getTabLayout().setupWithViewPager(mViewPager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_tvs, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_catalog:
                getActivity().startActivity(new Intent(getActivity(), CatalogActivity.class));
                return true;
            case R.id.action_search:
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_tvs;
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private ArrayList<String> titles = new ArrayList<>();

        public void addTab(String title){
            titles.add(title);
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TVsTabFragment.create(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
