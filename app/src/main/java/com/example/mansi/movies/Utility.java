package com.example.mansi.movies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import static android.content.Context.CONNECTIVITY_SERVICE;

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

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
