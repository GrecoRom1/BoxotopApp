package com.perrusset.romain.boxotop.UIL.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CastingList {

    public ArrayList<Cast> getActorList() {
        return actorList;
    }

    public void setActorList(ArrayList<Cast> actorList) {
        this.actorList = actorList;
    }

    @SerializedName("cast")
    private ArrayList<Cast> actorList;

    public ArrayList<Cast> getCrewList() {
        return crewList;
    }

    public void setCrewList(ArrayList<Cast> crewList) {
        this.crewList = crewList;
    }

    @SerializedName("crew")
    private ArrayList<Cast> crewList;


    public String getStringActorList() {
        StringBuilder actorListString = new StringBuilder();

        for (Cast c : actorList) {
            actorListString.append(c.name).append(", ");
        }

        return actorListString.toString();
    }

    public String getDirectorString() {
        return crewList.get(0).name;
    }
}
