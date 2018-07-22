package com.perrusset.romain.boxotop.UIL;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {

    @SerializedName("results")
    private ArrayList<Movie> movieList;

    public ArrayList<Movie> getNoticeArrayList() {
        return movieList;
    }

    public void setNoticeArrayList(ArrayList<Movie> noticeArrayList) {
        this.movieList = noticeArrayList;
    }
}
