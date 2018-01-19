package digi.mhr.digiforecast.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import digi.mhr.digiforecast.database.CacheField;
import digi.mhr.digiforecast.database.CacheModel;
import digi.mhr.digiforecast.network.api.APIs;
import digi.mhr.digiforecast.network.responses.GetCurrentWeatherResponse;
import digi.mhr.digiforecast.network.responses.GetForecastResponse;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by mohammad on 1/19/18.
 */

public class DataFactory {

    /*
     * Parameters:
     */
    private static DataFactory instance;
    private APIs apis;
    private Realm realm;

    /*
     * Constructors:
     */
    private DataFactory(Context context) {
        apis = new APIs(context);
        realm = Realm.getDefaultInstance();
    }

    /*
     * Singleton instance:
     */
    public static synchronized DataFactory getInstance(Context context) {
        if (instance == null) {
            instance = new DataFactory(context);
        }
        return instance;
    }

    /*
     * Functions:
     */
    /*
     * GET STORED:
     */
    public GetCurrentWeatherResponse getStoredCurrentWeatherResponse() {
        GetCurrentWeatherResponse response = null;

        RealmQuery<CacheModel> query = realm.where(CacheModel.class);
        query.equalTo("field", CacheField.CURRENT.getRawValue());
        CacheModel cacheModel = query.findFirst();

        if (cacheModel != null && cacheModel.getValue() != null) {
            Gson gson = new Gson();
            try {
                response = gson.fromJson(cacheModel.getValue(), GetCurrentWeatherResponse.class);
            } catch (ClassCastException|JsonSyntaxException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "error while parsing the stored response: " + cacheModel.getValue());
            }
        }

        return response;
    }

    public GetForecastResponse getStoredForecastResponse() {

        GetForecastResponse response = null;

        RealmQuery<CacheModel> query = realm.where(CacheModel.class);
        query.equalTo("field", CacheField.FORECAST.getRawValue());
        CacheModel cacheModel = query.findFirst();

        if (cacheModel != null && cacheModel.getValue() != null) {
            Gson gson = new Gson();
            try {
                response = gson.fromJson(cacheModel.getValue(), GetForecastResponse.class);
            } catch (ClassCastException|JsonSyntaxException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "error while parsing the stored response: " + cacheModel.getValue());
            }
        }

        return response;
    }

    /*
     * UPDATE:
     */
    private void updateCurrentWeatherData(String response) {
        CacheModel cacheModel = new CacheModel();
        cacheModel.setField(CacheField.CURRENT.getRawValue());
        cacheModel.setValue(response);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cacheModel);
        realm.commitTransaction();
    }

    private void updateForecastData(String response) {
        CacheModel cacheModel = new CacheModel();
        cacheModel.setField(CacheField.FORECAST.getRawValue());
        cacheModel.setValue(response);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cacheModel);
        realm.commitTransaction();
    }

    /*
     * REFRESH:
     */


}
