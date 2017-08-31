package com.example.mansi.movies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    private static final int LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Context context;
    private MovieAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mEmptyView;
    private GridView mGridview;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        context = getContext();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mGridview = (GridView) rootView.findViewById(R.id.gridview);
        mEmptyView = (TextView) rootView.findViewById(R.id.emptyView);

        if (!Utility.isNetworkConnected(getContext())) {
            mGridview.setEmptyView(mEmptyView);
            return rootView;
        }
        mProgressBar.setVisibility(View.VISIBLE);

        mAdapter = new MovieAdapter(getActivity(), movieArrayList);
        mGridview.setAdapter(mAdapter);

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie selectedMovie = (Movie) adapterView.getItemAtPosition(position);
                Intent openDetailActivity = new Intent(getActivity(), DetailActivity.class);
                openDetailActivity.putExtra("Movie_object", selectedMovie);
                startActivity(openDetailActivity);
            }
        });

        getLoaderManager().initLoader(LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new FetchMovieData(getContext());
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
