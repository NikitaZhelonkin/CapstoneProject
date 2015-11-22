package com.niksplay.moviesland.utils;

import java.util.Collection;

/**
 * Created by nikita on 21.11.15.
 */
public class ArrayUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
