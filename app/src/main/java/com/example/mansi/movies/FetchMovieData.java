package com.example.mansi.movies;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchMovieData extends AsyncTaskLoader<String> {
    private static final String BASEURL = "http://api.themoviedb.org/3/movie/";
    private static final String PATH = "popular";
    private static final String PATH1 = "top_rated";
    private static final String API_KEY = "api_key";
    private static final String LOG_TAG = FetchMovieData.class.getSimpleName();

    public FetchMovieData(Context context) {
        super(context);
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String json = null;
        Uri uri = Uri.parse(BASEURL)
                .buildUpon()
                .appendPath(PATH)
                .appendQueryParameter(API_KEY, BuildConfig.OPEN_MOVIE_DB_API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            json = readStream(inputStream);
            Log.v(LOG_TAG, json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    private String readStream(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        StringBuilder builder = new StringBuilder();
        String temp = br.readLine();
        while (temp != null) {
            builder.append(temp);
            temp = br.readLine();
        }
        return builder.toString();
    }

}
