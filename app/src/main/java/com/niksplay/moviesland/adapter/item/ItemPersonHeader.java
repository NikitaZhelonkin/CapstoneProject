package com.niksplay.moviesland.adapter.item;

import com.niksplay.moviesland.adapter.PersonDetailAdapter;
import com.niksplay.moviesland.model.Person;

/**
 * Created by nikita on 23.11.15.
 */
public class ItemPersonHeader extends SimpleItem<Person> {

    public ItemPersonHeader(Person data) {
        super(data, ItemType.TYPE_PERSON_HEADER);
    }
}
