package digi.mhr.digiforecast.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import digi.mhr.digiforecast.R;

/**
 * Created by mohammad on 1/15/18.
 */

public enum WeatherIcon implements Serializable {

    @SerializedName("01d")
    CLEAR_DAY(R.drawable.d01),
    @SerializedName("01n")
    CLEAR_NIGHT(R.drawable.n01),
    @SerializedName("02d")
    FEW_CLOUDS_DAY(R.drawable.d02),
    @SerializedName("02n")
    FEW_CLOUDS_NIGHT(R.drawable.n02),
    @SerializedName("03d")
    SCATTERED_CLOUDS_DAY(R.drawable.d03),
    @SerializedName("03n")
    SCATTERED_CLOUDS_NIGHT(R.drawable.n03),
    @SerializedName("04d")
    BROKEN_CLOUDS_DAY(R.drawable.d04),
    @SerializedName("04n")
    BROKEN_CLOUDS_NIGHT(R.drawable.n04),
    @SerializedName("09d")
    SHOWER_RAIN_DAY(R.drawable.d09),
    @SerializedName("09n")
    SHOWER_RAIN_NIGHT(R.drawable.n09),
    @SerializedName("10d")
    RAIN_DAY(R.drawable.d10),
    @SerializedName("10n")
    RAIN_NIGHT(R.drawable.n10),
    @SerializedName("11d")
    THUNDERSTORM_DAY(R.drawable.d11),
    @SerializedName("11n")
    THUNDERSTORM_NIGHT(R.drawable.n11),
    @SerializedName("13d")
    SNOW_DAY(R.drawable.d13),
    @SerializedName("13n")
    SNOW_NIGHT(R.drawable.n13),
    @SerializedName("50d")
    MIST_DAY(R.drawable.d50),
    @SerializedName("50n")
    MIST_NIGHT(R.drawable.n50);

    private int resValue;

    WeatherIcon(int resValue) {
        this.resValue = resValue;
    }

    public int getResValue() {
        return resValue;
    }

}
