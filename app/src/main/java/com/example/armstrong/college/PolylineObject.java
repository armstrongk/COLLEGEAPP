package com.example.armstrong.college;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PolylineObject {

    @SerializedName("points")
    @Expose
    private String points;

    /**
     *
     * @return
     * The points
     */
    public String getPoints() {
        return points;
    }

    /**
     *
     * @param points
     * The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

}
