package com.example.mansi.movies;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Movie[] objects) {
        super(context, resource, objects);
    }
}
