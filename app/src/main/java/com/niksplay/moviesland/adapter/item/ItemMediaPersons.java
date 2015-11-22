package com.niksplay.moviesland.adapter.item;

import android.support.v4.view.ViewPager;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.MediaDetailsAdapter;
import com.niksplay.moviesland.model.Credit;

import java.util.List;

import butterknife.Bind;

/**
 * Created by nikita on 21.11.15.
 */
public class ItemMediaPersons extends SimpleItem<List<Credit>> {

    public ItemMediaPersons(List<Credit> data) {
        super(data, MediaDetailsAdapter.TYPE_PERSONS);
    }


}
