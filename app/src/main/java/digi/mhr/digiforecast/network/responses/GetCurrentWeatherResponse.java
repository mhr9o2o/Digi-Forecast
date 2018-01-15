package digi.mhr.digiforecast.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import digi.mhr.digiforecast.models.Coordination;
import digi.mhr.digiforecast.models.SystemInformation;
import digi.mhr.digiforecast.models.TemperatureCondition;
import digi.mhr.digiforecast.models.WeatherCondition;
import digi.mhr.digiforecast.models.Wind;

/**
 * Created by mohammad on 1/15/18.
 */

public class GetCurrentWeatherResponse implements Serializable {

    @Expose
    @SerializedName("coord")
    private Coordination coordination;
    @Expose
    @SerializedName("weather")
    private List<WeatherCondition> weather;
    @Expose
    @SerializedName("main")
    private TemperatureCondition mainTempCondition;
    @Expose
    @SerializedName("visibility")
    private double visibility;
    @Expose
    @SerializedName("wind")
    private Wind wind;
    @Expose
    @SerializedName("dt")
    private long timestamp;
    @Expose
    @SerializedName("sys")
    private SystemInformation systemInformation;
    @Expose
    @SerializedName("id")
    private int cityId;
    @Expose
    @SerializedName("name")
    private String cityName;

    public Coordination getCoordination() {
        return coordination;
    }

    public void setCoordination(Coordination coordination) {
        this.coordination = coordination;
    }

    public List<WeatherCondition> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherCondition> weather) {
        this.weather = weather;
    }

    public TemperatureCondition getMainTempCondition() {
        return mainTempCondition;
    }

    public void setMainTempCondition(TemperatureCondition mainTempCondition) {
        this.mainTempCondition = mainTempCondition;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public SystemInformation getSystemInformation() {
        return systemInformation;
    }

    public void setSystemInformation(SystemInformation systemInformation) {
        this.systemInformation = systemInformation;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
