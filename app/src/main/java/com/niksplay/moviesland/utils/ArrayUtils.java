package com.niksplay.moviesland.utils;

import java.util.Collection;

/**
 * Created by nikita on 21.11.15.
 */
public class ArrayUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }


    public static int[] split(String delimiter, String text){
        String[] strArray = text!=null ? text.split(delimiter) : null;
        if (strArray != null) {
            int[] intArray = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }
            return intArray;
        }
        return null;
    }
}
