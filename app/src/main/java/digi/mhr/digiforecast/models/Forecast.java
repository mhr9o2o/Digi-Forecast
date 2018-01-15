package digi.mhr.digiforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammad on 1/15/18.
 */

public class Forecast implements Serializable {

    @Expose
    @SerializedName("dt")
    private long timestamp;
    @Expose
    @SerializedName("main")
    private TemperatureCondition temperatureCondition;
    @Expose
    @SerializedName("weather")
    private List<WeatherCondition> weather = new ArrayList<>();
    @Expose
    @SerializedName("wind")
    private Wind wind;
    @Expose
    @SerializedName("dt_text")
    private String timeText;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public TemperatureCondition getTemperatureCondition() {
        return temperatureCondition;
    }

    public void setTemperatureCondition(TemperatureCondition temperatureCondition) {
        this.temperatureCondition = temperatureCondition;
    }

    public List<WeatherCondition> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherCondition> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }
}
