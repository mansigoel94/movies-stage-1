package com.example.mansi.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    TextView mSynopsis;
    ImageView mImageView;
    TextView mRatings;
    TextView mReleaseDate;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Movie movieToDisplay = (Movie) getActivity().getIntent()
                .getParcelableExtra(getString(R.string.open_detail_intent_key));

        String title = movieToDisplay.getTitle();
        if (title != null) {
            getActivity().setTitle(title);
        }
        mImageView = (ImageView) rootView.findViewById(R.id.detail_poster);
        mSynopsis = (TextView) rootView.findViewById(R.id.detail_synopsis);
        mRatings = (TextView) rootView.findViewById(R.id.detail_ratings);
        mReleaseDate = (TextView) rootView.findViewById(R.id.detail_release_date);

        //displaying poster
        String relativeUrl = movieToDisplay.getPoster();
        if (relativeUrl != null && !TextUtils.isEmpty(relativeUrl))
            Picasso.with(getContext())
                    .load(Utility.getAbsoluteUrlForPoster(relativeUrl))
                    .into(mImageView);

        String releaseDate = movieToDisplay.getReleaseDate();
        if (releaseDate != null && !TextUtils.isEmpty(releaseDate)) {
            mReleaseDate.setText(Utility.formatDate(getContext(), releaseDate));
        }
        //displaying synopsis
        String synopsis = movieToDisplay.getSynopsis();
        if (synopsis != null && !TextUtils.isEmpty(synopsis))
            mSynopsis.setText(synopsis);

        //displaying ratings
        if (movieToDisplay.getRatings() != -1) {
            String ratings = String.format(getString(R.string.format_ratings), movieToDisplay.getRatings());
            mRatings.setText(ratings);
        }
        return rootView;
    }
}
