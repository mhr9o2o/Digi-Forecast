package digi.mhr.digiforecast.models;

import android.support.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import digi.mhr.digiforecast.R;

/**
 * Created by mohammad on 1/15/18.
 */

public class WeatherCondition implements Serializable {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("main")
    private String main;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("icon")
    private WeatherIcon icon;

    public WeatherIcon getIcon() {
        return icon;
    }

    public void setIcon(WeatherIcon icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
