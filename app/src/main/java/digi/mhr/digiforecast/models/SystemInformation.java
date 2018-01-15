package digi.mhr.digiforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohammad on 1/15/18.
 */

public class SystemInformation implements Serializable {

    @Expose
    @SerializedName("type")
    private int type;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("message")
    private double message;
    @Expose
    @SerializedName("country")
    private String country;
    @Expose
    @SerializedName("sunrise")
    private long sunriseTimestamp;
    @Expose
    @SerializedName("sunset")
    private long sunsetTimestamp;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    public void setSunriseTimestamp(long sunriseTimestamp) {
        this.sunriseTimestamp = sunriseTimestamp;
    }

    public long getSunsetTimestamp() {
        return sunsetTimestamp;
    }

    public void setSunsetTimestamp(long sunsetTimestamp) {
        this.sunsetTimestamp = sunsetTimestamp;
    }
}
