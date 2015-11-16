package com.niksplay.moviesland.model;

/**
 * Created by nikita on 15.11.15.
 */
public class Genre {

    public int id;
    public String name;

    @Override
    public boolean equals(Object o) {
        return o instanceof Genre && id == ((Genre) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
