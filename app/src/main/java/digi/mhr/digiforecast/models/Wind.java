package digi.mhr.digiforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohammad on 1/15/18.
 */

public class Wind implements Serializable {

    @Expose
    @SerializedName("speed")
    private double speed;
    @Expose
    @SerializedName("deg")
    private int degree;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
