package com.niksplay.moviesland.adapter.item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niksplay.moviesland.R;
import com.niksplay.moviesland.adapter.holder.AbsViewHolder;
import com.niksplay.moviesland.adapter.holder.PersonDetailHolder;
import com.niksplay.moviesland.model.Person;

/**
 * Created by nikita on 23.11.15.
 */
public class ItemPersonHeader extends SimpleItem<Person> {

    public ItemPersonHeader(Person data) {
        super(data, ItemType.TYPE_PERSON_HEADER);
    }

    @Override
    public AbsViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new PersonDetailHolder(inflater.inflate(R.layout.list_item_person_header, parent, false));
    }
}
