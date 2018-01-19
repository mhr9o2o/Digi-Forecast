package digi.mhr.digiforecast.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import digi.mhr.digiforecast.R;
import digi.mhr.digiforecast.network.responses.GetCurrentWeatherResponse;

public class MainActivity extends AppCompatActivity {

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
    private TextView windVelocityTV;
    private TextView windDegreeTV;
    /*
     * * Activity Views:
     */
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView regionInfoTV;
    private TextView lastUpdateLabelTV;
    private TextView lastUpdateTV;

    /*
     * Activity Functions:
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
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
        windVelocityTV = findViewById(R.id.item_weather_detail_wind_velocity);
        windDegreeTV = findViewById(R.id.item_weather_detail_wind_degree);
        //
        swipeRefreshLayout = findViewById(R.id.activity_main_refresh_layout);
        regionInfoTV = findViewById(R.id.activity_main_region_info);
        lastUpdateLabelTV = findViewById(R.id.activity_main_last_update_label);
        lastUpdateTV = findViewById(R.id.activity_main_last_update);
    }



}
