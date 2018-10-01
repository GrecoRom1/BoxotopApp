package com.perrusset.romain.boxotop.UIL.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Movie {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String synopsis;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("runtime")
    private int durationInMinute;

    @SerializedName("vote_average")
    private float voteAverage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {

        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");

        Date date = null;
        try {
            date = format.parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.releaseDate = df.format(releaseDate);
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getDurationInMinute() {
        return durationInMinute;
    }

    public void setDurationInMinute(int durationInMinute) {
        this.durationInMinute = durationInMinute;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }



    public Movie() {

    }

    public String getDurationFormattedString() {
        int hours = durationInMinute / 60;
        int minutes = durationInMinute % 60;

        String minuteString = "";
        if ((minutes % 10) == 0) {
            minuteString = "0";
        }
        minuteString += Integer.toString(minutes);

        return Integer.toString(hours) + " h " + minuteString;
    }

    public String getURLFormattedString() {
        return "http://image.tmdb.org/t/p/w185" + posterPath;
    }

    public float getVoteAverage() {
        return voteAverage / 2.0f;
    }
}

