package digi.mhr.digiforecast.database;

/**
 * Created by mohammad on 1/16/18.
 */

public enum CacheField {

    FORECAST("forecast"),
    CURRENT("current");

    private String rawValue;

    CacheField(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getRawValue() {
        return rawValue;
    }
}
