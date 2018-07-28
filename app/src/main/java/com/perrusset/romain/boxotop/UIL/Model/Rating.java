package com.perrusset.romain.boxotop.UIL.Model;

import com.google.gson.annotations.SerializedName;

public class Rating{
    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String mark;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
