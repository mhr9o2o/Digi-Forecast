package digi.mhr.digiforecast.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import digi.mhr.digiforecast.R;
import digi.mhr.digiforecast.models.TemperatureCondition;
import digi.mhr.digiforecast.models.WeatherCondition;
import digi.mhr.digiforecast.models.Wind;
import digi.mhr.digiforecast.presenters.MainPresenterImp;
import digi.mhr.digiforecast.utilities.FallbackLocationTracker;
import digi.mhr.digiforecast.views.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    /*
     * Views:
     */
    /*
     * * Weather Detail Views:
     */
    private LinearLayout weatherDetailHolder;
    private TextView maxTemperatureTV;
    private TextView minTemperatureTV;
    private TextView currentTemperatureTV;
    private ImageView currentConditionIV;
    private TextView conditionDescriptionTV;
    private TextView humidityTV;
    private TextView windSpeedTV;
    private TextView windDegreeTV;
    /*
     * * Activity Views:
     */
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView regionInfoTV;
    private TextView lastUpdateLabelTV;
    private TextView lastUpdateTV;

    /*
     * Parameters:
     */
    private final long LOCATION_REFRESH_TIME = 60*1000; // 1 second
    private final float LOCATION_REFRESH_DISTANCE = 10*1000; // 10 KM
    private boolean hasFineLocationAccess = false;
    private boolean hasCoarseLocationAccess = false;
    private FallbackLocationTracker locationTracker;
    private MainPresenterImp mainPresenter;

    /*
     * Activity Functions:
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * Checking location permission:
         */
        int fineLocationPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationPermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (fineLocationPermissionCheck == PackageManager.PERMISSION_GRANTED) {
            hasFineLocationAccess = true;
        }

        if (coarseLocationPermissionCheck == PackageManager.PERMISSION_GRANTED) {
            hasCoarseLocationAccess = true;
        }

        /*
         * Binding Views
         */
        bindViews();

        /*
         * Initializing the Presenter
         */
        mainPresenter = new MainPresenterImp(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!hasFineLocationAccess && !hasCoarseLocationAccess) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.ACCESS_FINE_LOCATION },
                    0);
            mainPresenter.onResume(false);
        } else {
            locationTracker = new FallbackLocationTracker(this);
            mainPresenter.setLocationTracker(locationTracker);
            mainPresenter.onResume(true);
        }
    }

    /*
     * Permission Callbacks:
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            locationTracker = new FallbackLocationTracker(this);

            mainPresenter.setLocationTracker(locationTracker);

            mainPresenter.onPermissionApproved();

        } else {
            mainPresenter.onPermissionDenied();
        }
        return;
    }

    /*
     * Binding Functions:
     */
    private void bindViews() {
        weatherDetailHolder = findViewById(R.id.include);
        maxTemperatureTV = findViewById(R.id.item_weather_detail_max_temp);
        minTemperatureTV = findViewById(R.id.item_weather_detail_min_temp);
        currentTemperatureTV = findViewById(R.id.item_weather_detail_temp);
        currentConditionIV = findViewById(R.id.item_weather_detail_icon);
        conditionDescriptionTV = findViewById(R.id.item_weather_detail_description);
        humidityTV = findViewById(R.id.item_weather_detail_humidity);
        windSpeedTV = findViewById(R.id.item_weather_detail_wind_speed);
        windDegreeTV = findViewById(R.id.item_weather_detail_wind_degree);
        //
        swipeRefreshLayout = findViewById(R.id.activity_main_refresh_layout);
        regionInfoTV = findViewById(R.id.activity_main_region_info);
        lastUpdateLabelTV = findViewById(R.id.activity_main_last_update_label);
        lastUpdateTV = findViewById(R.id.activity_main_last_update);
    }

    /*
     * View Functions:
     */
    @Override
    public void setInitialViewState() {
        weatherDetailHolder.setVisibility(View.GONE);
        lastUpdateLabelTV.setVisibility(View.GONE);
    }

    @Override
    public void configureViewAfterInitializing() {
        weatherDetailHolder.setVisibility(View.VISIBLE);
        lastUpdateLabelTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setTemperatureData(TemperatureCondition temperatureCondition) {
        String celsiusSuffix = getResources().getString(R.string.celsius_degree);
        String percentSuffix = getResources().getString(R.string.percent);
        String currentTemp = String.valueOf(temperatureCondition.getTemperature()) + celsiusSuffix;
        String minTemp = String.valueOf(temperatureCondition.getMinimumTemperature()) + celsiusSuffix;
        String maxTemp = String.valueOf(temperatureCondition.getMaximumTemperature()) + celsiusSuffix;
        String humidity = String.valueOf(temperatureCondition.getHumidity()) + percentSuffix;
        currentTemperatureTV.setText(currentTemp);
        minTemperatureTV.setText(minTemp);
        maxTemperatureTV.setText(maxTemp);
        humidityTV.setText(humidity);
    }

    @Override
    public void setWeatherConditionData(WeatherCondition weatherCondition) {
        currentConditionIV.setImageResource(weatherCondition.getIcon().getResValue());
        conditionDescriptionTV.setText(weatherCondition.getDescription());
    }

    @Override
    public void setWindData(Wind wind) {
        String degreeSuffix = getResources().getString(R.string.degree_sign);
        String speedSuffix = getResources().getString(R.string.kilometer_per_hour);
        String windDegree = String.valueOf(wind.getDegree()) + degreeSuffix;
        String windSpeed = String.valueOf(wind.getSpeed()) + speedSuffix;
        windDegreeTV.setText(windDegree);
        windSpeedTV.setText(windSpeed);
    }

    @Override
    public void setLastUpdate(String lastUpdate) {
        lastUpdateTV.setText(lastUpdate);
    }

    @Override
    public void setRegionInfoData(String region) {
        regionInfoTV.setText(region);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(int errorResId) {
        Toast.makeText(this, errorResId, Toast.LENGTH_SHORT).show();
    }
}
