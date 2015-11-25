package com.niksplay.moviesland.utils;

import android.text.TextUtils;
import android.util.Log;

import com.niksplay.moviesland.managers.Genres;
import com.niksplay.moviesland.model.Genre;
import com.niksplay.moviesland.model.IMedia;
import com.niksplay.moviesland.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nikita on 16.11.15.
 */
public class Utils {

    public static int getYear(String dateString) {
        try {
            if (TextUtils.isEmpty(dateString)) {
                return 0;
            }
            Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            return 0;
        }
    }

    public static String formatGenres(IMedia iMovie) {
        Genre[] genres = iMovie.getGenres();
        if (genres == null) {
            if (iMovie.getGenreIds() == null) {
                return "";
            }
            genres = new Genre[iMovie.getGenreIds().length];
            for (int i = 0; i < iMovie.getGenreIds().length; i++) {
                genres[i] = Genres.getGenre(iMovie.getGenreIds()[i]);
            }
        }
        return TextUtils.join(", ", genres);
    }

    public static String formatKnownFor(Person person){
        StringBuilder builder = new StringBuilder();
        if(person.knownFor == null){
            return "";
        }
        for (IMedia media : person.knownFor) {
            builder.append(media.getTitle()).append(", ");
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 2, builder.length());
            return builder.toString();
        }else{
            return "";
        }
    }
}
