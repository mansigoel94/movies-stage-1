package com.example.mansi.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView mSynopsis;
    ImageView mImageView;
    TextView mRatings;
    TextView mReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movieToDisplay = (Movie) getIntent().getSerializableExtra("Movie_object");

        String title = movieToDisplay.getTitle();
        if (title != null) {
            setTitle(title);
        }
        mImageView = (ImageView) findViewById(R.id.detail_poster);
        mSynopsis = (TextView) findViewById(R.id.detail_synopsis);
        mRatings = (TextView) findViewById(R.id.detail_ratings);
        mReleaseDate = (TextView) findViewById(R.id.detail_release_date);

        //displaying poster
        String relativeUrl = movieToDisplay.getPoster();
        if (relativeUrl != null && !TextUtils.isEmpty(relativeUrl))
            Picasso.with(getBaseContext())
                    .load(Utility.getAbsoluteUrlForPoster(relativeUrl))
                    .into(mImageView);

        String releaseDate = movieToDisplay.getReleaseDate();
        if (releaseDate != null && !TextUtils.isEmpty(releaseDate)) {
            mReleaseDate.setText(Utility.formatDate(getBaseContext(), releaseDate));
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
    }
}
