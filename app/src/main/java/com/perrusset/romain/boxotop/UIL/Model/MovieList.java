package com.perrusset.romain.boxotop.UIL.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {

    @SerializedName("results")
    private ArrayList<Movie> movieList;

    public ArrayList<Movie> getMovieArrayList() {

        if (movieList != null) {
            return movieList;
        } else {
            return new ArrayList<>();
        }
    }

    public void setMovieArrayList(ArrayList<Movie> noticeArrayList) {
        this.movieList = noticeArrayList;
    }
}
