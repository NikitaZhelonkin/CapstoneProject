package com.niksplay.moviesland.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.fragment.PersonFragment;
import com.niksplay.moviesland.model.Person;

/**
 * Created by nikita on 22.11.15.
 */
public class PersonActivity extends AppCompatActivity {

    private static final String EXTRA_PERSON = "extra_person";

    public static Intent createIntent(Context context, Person person){
        Intent i = new Intent(context, PersonActivity.class);
        i.putExtra(EXTRA_PERSON, person);
        return i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Person person =  getIntent().getExtras().getParcelable(EXTRA_PERSON);

        if (savedInstanceState == null) {
            PersonFragment fragment = PersonFragment.create(person);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
