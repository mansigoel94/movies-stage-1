package com.example.mansi.movies;


import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
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

    public Movie(Parcel in) {
        mTitle = in.readString();
        mPoster = in.readString();
        mSynopsis = in.readString();
        mRatings = in.readDouble();
        mReleaseDate = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mPoster);
        parcel.writeString(mSynopsis);
        parcel.writeDouble(mRatings);
        parcel.writeString(mReleaseDate);
    }
}
