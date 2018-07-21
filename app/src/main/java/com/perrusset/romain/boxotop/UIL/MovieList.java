package com.perrusset.romain.boxotop.UIL;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {

    @SerializedName("movie_list")
    private ArrayList<Movie> noticeList;

    public ArrayList<Movie> getNoticeArrayList() {
        return noticeList;
    }

    public void setNoticeArrayList(ArrayList<Movie> noticeArrayList) {
        this.noticeList = noticeArrayList;
    }
}
