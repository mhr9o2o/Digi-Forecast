package digi.mhr.digiforecast.network.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import digi.mhr.digiforecast.network.responses.GetCurrentWeatherResponse;
import digi.mhr.digiforecast.utilities.NetworkUtils;

/**
 * Created by mohammad on 1/19/18.
 */

public class APIs {

    /*
     * Constants:
     */
    public final static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public final static String APP_ID = "22a24624399e846330b2f3b5614b598b";
    public final static String APP_ID_KEY = "appid";
    public final static String UNITS_KEY = "units";
    public final static String UNITS_VALUE = "metric";
    private final String WEATHER_KEY = "weather";
    private final String FORECAST_KEY = "forecast";

    /*
     * Parameters:
     */
    private RequestQueue requestQueue;

    /*
     * Constructors
     */
    public APIs(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    /*
     * APIs:
     */

    /**
     * Gets current weather condition from server
     * @param latitude latitude of user's current location
     * @param longitude longitude of user's current location
     * @param listener response listener, will be fired when a successful response is received from server
     * @param errorListener error listener, will be fired when an error is received from server
     */
    public void getCurrentWeatherCondition(double latitude, double longitude, final Response.Listener<String> listener,
                                                  final Response.ErrorListener errorListener) {

        Map<String, String> params = new HashMap<>();
        params.put("lat", Double.toString(latitude));
        params.put("lon", Double.toString(longitude));

        String url = NetworkUtils.createRequestURL(WEATHER_KEY, params);

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        requestQueue.add(request);

    }

    /**
     * Gets forecast for 5 days from Server divided into 40 items for every 3 hours
     * @param latitude latitude of user's current location
     * @param longitude longitude of user's current location
     * @param listener response listener, will be fired when a successful response is received from server
     * @param errorListener error listener, will be fired when an error is received from server
     */
    public void getFiveDayForecast(double latitude, double longitude, final Response.Listener<String> listener,
                                   final Response.ErrorListener errorListener) {

        Map<String, String> params = new HashMap<>();
        params.put("lat", Double.toString(latitude));
        params.put("lon", Double.toString(longitude));

        String url = NetworkUtils.createRequestURL(FORECAST_KEY, params);

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        requestQueue.add(request);

    }


}
