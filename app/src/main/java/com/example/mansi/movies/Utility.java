package com.example.mansi.movies;

import android.net.Uri;

public class Utility {
    public static final String BASEURL = "http://image.tmdb.org/t/p/";
    final static String PATH = "w185";

    public static Uri getAbsoluteUrlForPoster(String relativeUrl) {
        Uri uri = Uri.parse(BASEURL)
                .buildUpon()
                .appendPath(PATH)
                .appendPath(relativeUrl.substring(1))
                .build();
        return uri;
    }
}
