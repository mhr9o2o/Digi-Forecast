package digi.mhr.digiforecast.presenters;

import android.util.Log;

import digi.mhr.digiforecast.R;
import digi.mhr.digiforecast.data.DataFactory;
import digi.mhr.digiforecast.data.DataListener;
import digi.mhr.digiforecast.models.Coordination;
import digi.mhr.digiforecast.network.responses.GetCurrentWeatherResponse;
import digi.mhr.digiforecast.utilities.Location.FallbackLocationTracker;
import digi.mhr.digiforecast.utilities.TimeUtils;
import digi.mhr.digiforecast.views.MainView;

/**
 * Created by mohammad on 1/19/18.
 */

public class MainPresenterImp implements MainPresenter {

    /*
     * Parameters
     */
    private MainView mainView;
    private FallbackLocationTracker locationTracker;
    private Coordination coordination;
    private DataListener<GetCurrentWeatherResponse> dataListener;

    /*
     * Constructors and Setters/Getters
     */
    public MainPresenterImp(final MainView mainView) {
        this.mainView = mainView;
        coordination = new Coordination();

        dataListener = new DataListener<GetCurrentWeatherResponse>() {
            @Override
            public void onDataReceived(GetCurrentWeatherResponse data) {
                initResponseOnView(data);
                mainView.hideLoading();
            }

            @Override
            public void onError(String error) {
                Log.e("Network Error", error);
                mainView.showError(error);
                mainView.hideLoading();
            }
        };

    }

    public void setLocationTracker(FallbackLocationTracker locationTracker) {
        this.locationTracker = locationTracker;
        coordination = new Coordination();
        if (locationTracker.getLocation() != null) {
            coordination.setLatitude(locationTracker.getLocation().getLatitude());
            coordination.setLongitude(locationTracker.getLocation().getLongitude());
        } else if (locationTracker.getPossiblyStaleLocation() != null) {
            coordination.setLatitude(locationTracker.getPossiblyStaleLocation().getLatitude());
            coordination.setLongitude(locationTracker.getPossiblyStaleLocation().getLongitude());
        }
    }

    /*
     * Functions
     */
    // it's public just for unit test
    public void initResponseOnView(GetCurrentWeatherResponse response) {

        mainView.configureViewAfterInitializing();

        if (response != null) {
        /*
         * Region:
         */
            String regionInfo = "";

            if (response.getCityName() != null) {
                regionInfo = response.getCityName();
            }

            if (response.getSystemInformation() != null && response.getSystemInformation().getCountry() != null) {
                regionInfo += ", " + response.getSystemInformation().getCountry();
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
            if (response.getTimestamp() != 0) {
                mainView.setLastUpdate(TimeUtils.getTimeAgo(response.getTimestamp()));
            }
        }
    }

    /*
     * Presenter Functions
     */
    @Override
    public void onPermissionApproved() {
        mainView.configureViewAfterInitializing();
        onRefresh();
    }

    @Override
    public void onPermissionDenied() {
        mainView.showError(R.string.location_permission_denied_message);
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
        if (locationTracker != null) {
            if (locationTracker.getLocation() != null) {
                coordination.setLatitude(locationTracker.getLocation().getLatitude());
                coordination.setLongitude(locationTracker.getLocation().getLongitude());
            } else if (locationTracker.getPossiblyStaleLocation() != null) {
                coordination.setLatitude(locationTracker.getPossiblyStaleLocation().getLatitude());
                coordination.setLongitude(locationTracker.getPossiblyStaleLocation().getLongitude());
            }
        }

        if (DataFactory.getInstance() != null) {
            DataFactory.getInstance().refreshCurrentWeather(coordination.getLatitude(), coordination.getLongitude(), dataListener);
        }
    }
}
