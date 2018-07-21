package com.perrusset.romain.boxotop.UIL;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Movie {

    @SerializedName("id")
    public int id;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public int year;

    @SerializedName("Released")
    public Date releaseDate;

    @SerializedName("Runtime")
    public int runTime;

    @SerializedName("Genre")
    public String genre;

    @SerializedName("Director")
    public String director;

    @SerializedName("Writer")
    public String writer;

    @SerializedName("Language")
    public String language;

    @SerializedName("Country")
    public String country;

    @SerializedName("Actors")
    public String actors;

    @SerializedName("Poster")
    public String urlPoster;

    @SerializedName("Plot")
    public String synopsis;

    @SerializedName("Ratings")
    public List<Rating> ratingList;

    public Movie(){

    }

}

