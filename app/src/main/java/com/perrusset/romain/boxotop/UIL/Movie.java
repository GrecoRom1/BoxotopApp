package com.perrusset.romain.boxotop.UIL;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("overview")
    public String synopsis;

    @SerializedName("vote_average")
    public String vote;

    @SerializedName("poster_path")
    public String posterPath;

    public Movie(){

    }

}

