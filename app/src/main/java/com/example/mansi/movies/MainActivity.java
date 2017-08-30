package com.example.mansi.movies;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    private static final int LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private MovieAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mEmptyView;
    private GridView mGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mGridview = (GridView) findViewById(R.id.gridview);
        mEmptyView = (TextView) findViewById(R.id.emptyView);

        if (!isNetworkConnected()) {
            mGridview.setEmptyView(mEmptyView);
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);

        mAdapter = new MovieAdapter(getBaseContext(), movieArrayList);
        mGridview.setAdapter(mAdapter);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new FetchMovieData(getBaseContext());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> movies) {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addAll(movies);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
        mAdapter.clear();
    }
}
