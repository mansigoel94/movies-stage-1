package com.example.mansi.movies;


import java.util.ArrayList;

public class Movie {
    private String mTitle;
    private String mPoster;
    private String mSynopsis;
    private double mRatings;
    private String mReleaseDate;

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
