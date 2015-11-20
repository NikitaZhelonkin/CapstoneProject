package com.niksplay.moviesland.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.fragment.FavoriteFragment;
import com.niksplay.moviesland.fragment.MoviesFragment;
import com.niksplay.moviesland.fragment.PersonsFragment;
import com.niksplay.moviesland.fragment.TVsFragment;
import com.niksplay.moviesland.fragment.WatchlistFragment;
import com.niksplay.moviesland.managers.Genres;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.nav_view) NavigationView mNavigationView;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Genres.loadGenres();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            showNavigationFragment(R.id.nav_movies);
        }
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
}
