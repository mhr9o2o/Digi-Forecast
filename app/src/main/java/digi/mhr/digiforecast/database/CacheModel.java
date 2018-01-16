package digi.mhr.digiforecast.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mohammad on 1/16/18.
 */

public class CacheModel extends RealmObject {

    @PrimaryKey
    private String field; // The field parameter is actually the raw value of the CacheField enum. right now there's only two fields: Forecast or Current
    private String value; // It will be the raw JSON response from server

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
