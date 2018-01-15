package digi.mhr.digiforecast.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import digi.mhr.digiforecast.models.City;
import digi.mhr.digiforecast.models.Forecast;

/**
 * Created by mohammad on 1/15/18.
 */

public class GetForecastResponse implements Serializable {

    @Expose
    @SerializedName("cnt")
    private int count;
    @Expose
    @SerializedName("list")
    private List<Forecast> forecasts = new ArrayList<>();
    @Expose
    @SerializedName("city")
    private City city;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
