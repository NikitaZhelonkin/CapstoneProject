package com.niksplay.moviesland.adapter;

import android.app.Activity;

import com.niksplay.moviesland.activity.MediaDetailActivity;
import com.niksplay.moviesland.activity.PersonActivity;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Person;

/**
 * Created by nikita on 27.11.15.
 */
public class PersonSelectedListener implements PersonsAdapter.OnItemSelectedListener {

    private Activity activity;

    public PersonSelectedListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onItemSelected(Person person) {
        activity.startActivity(PersonActivity.createIntent(activity, person));
    }
}
