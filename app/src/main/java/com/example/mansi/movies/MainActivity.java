package com.example.mansi.movies;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final int LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        ArrayList<String> list = new ArrayList<>();
        list.add("asjgjhas");
        list.add("dhkjhd");
        list.add("ijrdjf");
        ArrayAdapter<String> adapter = new ArrayAdapter(getBaseContext(), R.layout.gridlayout, R.id.textView, list);
        gridView.setAdapter(adapter);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "OnCreateLoader");
        return new FetchMovieData(getBaseContext());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
