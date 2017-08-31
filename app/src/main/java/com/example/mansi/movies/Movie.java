package com.example.mansi.movies;

import java.io.Serializable;

public class Movie implements Serializable {
    private String mTitle = null;
    private String mPoster = null;
    private String mSynopsis = null;
    private double mRatings = -1;
    private String mReleaseDate = null;

    public Movie(String title, String poster, String synopsis, double ratings, String releaseDate) {
        mTitle = title;
        mPoster = poster;
        mSynopsis = synopsis;
        mRatings = ratings;
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPoster() {
        return mPoster;
    }

    public double getRatings() {
        return mRatings;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getSynopsis() {
        return mSynopsis;
    }
}
