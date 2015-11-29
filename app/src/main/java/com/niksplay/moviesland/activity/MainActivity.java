package com.niksplay.moviesland.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.niksplay.moviesland.R;
import com.niksplay.moviesland.fragment.FavoriteFragment;
import com.niksplay.moviesland.fragment.MoviesFragment;
import com.niksplay.moviesland.fragment.PersonsFragment;
import com.niksplay.moviesland.fragment.TVsFragment;
import com.niksplay.moviesland.fragment.WatchlistFragment;
import com.niksplay.moviesland.managers.Genres;
import com.niksplay.moviesland.network.MoviesService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.nav_view) NavigationView mNavigationView;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    @Bind(R.id.adView) AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TextUtils.isEmpty(MoviesService.API_KEY)) {
            showAlertDialog(getString(R.string.provide_api_key));
        }

        Genres.loadGenres();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            showNavigationFragment(R.id.nav_movies);
        }

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(getString(R.string.admob_device_id)).build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        showNavigationFragment(item.getItemId());
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public TabLayout getTabLayout(){
        return mTabLayout;
    }

    private void showNavigationFragment(int navId){
        mNavigationView.setCheckedItem(navId);
        Fragment fragment;
        switch (navId) {
            default:
            case R.id.nav_movies:
                fragment = new MoviesFragment();
                break;
            case R.id.nav_tvs:
                fragment = new TVsFragment();
                break;
            case R.id.nav_actors:
                fragment = new PersonsFragment();
                break;
            case R.id.nav_favorite:
                fragment = new FavoriteFragment();
                break;
            case R.id.nav_watchlist:
                fragment = new WatchlistFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, null);
        builder.create().show();
    }
}
