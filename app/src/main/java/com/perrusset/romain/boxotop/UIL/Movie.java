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

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("runtime")
    public int durationInMinute;


    @SerializedName("vote_average")
    public float voteAverage;

    public Movie() {

    }

    public String getDurationFormattedString() {
        int hours = durationInMinute / 60;
        int minutes = durationInMinute % 60;
        String durationString = Integer.toString(hours) + " h" + Integer.toString(minutes);

        return durationString;
    }

    public String getURLFormattedString(){
        String urlFormatted = "http://image.tmdb.org/t/p/w185"+posterPath;

        return urlFormatted;
    }

    public float getVoteAverage() {
        return voteAverage/2.0f;
    }
}

