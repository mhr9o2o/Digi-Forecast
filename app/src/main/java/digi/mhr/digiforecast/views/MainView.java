package digi.mhr.digiforecast.views;

import android.support.annotation.IdRes;

import digi.mhr.digiforecast.models.TemperatureCondition;
import digi.mhr.digiforecast.models.WeatherCondition;
import digi.mhr.digiforecast.models.Wind;

/**
 * Created by mohammad on 1/19/18.
 */

public interface MainView {

    void setInitialViewState();
    void configureViewAfterInitializing();
    void showLoading();
    void hideLoading();
    void setTemperatureData(TemperatureCondition temperatureCondition);
    void setWeatherConditionData(WeatherCondition weatherCondition);
    void setWindData(Wind wind);
    void setLastUpdate(String lastUpdate);
    void setRegionInfoData(String region);
    void showError(String error);
    @IdRes
    void showError(int errorResId);

}
