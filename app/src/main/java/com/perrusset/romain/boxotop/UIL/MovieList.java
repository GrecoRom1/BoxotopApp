package com.perrusset.romain.boxotop.UIL;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {

    @SerializedName("results")
    private ArrayList<Movie> movieList;

    public ArrayList<Movie> getMovieArrayList() {
        return movieList;
    }

    public void setMovieArrayList(ArrayList<Movie> noticeArrayList) {
        this.movieList = noticeArrayList;
    }
}
