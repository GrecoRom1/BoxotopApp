package com.perrusset.romain.boxotop.UIL;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CastingList {

    @SerializedName("cast")
    private ArrayList<Cast> castList;

    public ArrayList<Cast> getCastingArrayList() {
        return castList;
    }

    public void setMovieArrayList(ArrayList<Cast> arrayList) {
        this.castList = arrayList;
    }
}
