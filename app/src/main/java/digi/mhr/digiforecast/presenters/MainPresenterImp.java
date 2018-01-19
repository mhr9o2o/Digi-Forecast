package digi.mhr.digiforecast.presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import digi.mhr.digiforecast.data.DataFactory;
import digi.mhr.digiforecast.data.DataListener;
import digi.mhr.digiforecast.models.Coordination;
import digi.mhr.digiforecast.network.responses.GetCurrentWeatherResponse;
import digi.mhr.digiforecast.views.MainView;

/**
 * Created by mohammad on 1/19/18.
 */

public class MainPresenterImp implements MainPresenter {

    /*
     * Parameters
     */
    private MainView mainView;
    private LocationListener locationListener;
    private Coordination coordination;
    private DataListener<GetCurrentWeatherResponse> dataListener;

    /*
     * Constructors and Setters/Getters
     */
    public MainPresenterImp(final MainView mainView) {
        this.mainView = mainView;
        coordination = new Coordination();

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                coordination.setLatitude(location.getLatitude());
                coordination.setLongitude(location.getLongitude());
                onRefresh();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        dataListener = new DataListener<GetCurrentWeatherResponse>() {
            @Override
            public void onDataReceived(GetCurrentWeatherResponse data) {
                initResponseOnView(data);
                mainView.hideLoading();
            }

            @Override
            public void onError(String error) {
                // TODO
            }
        };

    }

    public LocationListener getLocationListener() {
        return locationListener;
    }

    /*
     * Functions
     */
    private void initResponseOnView(GetCurrentWeatherResponse response) {
        /*
         * Region:
         */
        String regionInfo = "";

        if (response.getCityName() != null) {
            regionInfo = response.getCityName();
        }

        if (response.getSystemInformation() != null && response.getSystemInformation().getCountry() != null) {
            regionInfo += response.getSystemInformation().getCountry();
        }

        mainView.setRegionInfoData(regionInfo);
        /*
         * Temperature & humidity:
         */
        if (response.getMainTempCondition() != null) {
            mainView.setTemperatureData(response.getMainTempCondition());
        }
        /*
         * Condition:
         */
        if (response.getWeather() != null && response.getWeather().size() > 0) {
            mainView.setWeatherConditionData(response.getWeather().get(0));
        }
        /*
         * Wind:
         */
        if (response.getWind() != null) {
            mainView.setWindData(response.getWind());
        }
        /*
         * Last Update:
         */
        // TODO
    }

    /*
     * Presenter Functions
     */
    @Override
    public void onPermissionApproved() {
        mainView.configureViewAfterInitializing();
    }

    @Override
    public void onPermissionDenied() {
        // TODO
    }

    @Override
    public void onResume(boolean hasLocationPermission) {
        mainView.setInitialViewState();
        if (hasLocationPermission) {
            GetCurrentWeatherResponse response = DataFactory.getInstance().getStoredCurrentWeatherResponse();
            if (response != null) {
                initResponseOnView(response);
            }
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mainView.showLoading();
        DataFactory.getInstance().refreshCurrentWeather(coordination.getLatitude(), coordination.getLongitude(), dataListener);
    }
}
