package digi.mhr.digiforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohammad on 1/15/18.
 */

public class Coordination implements Serializable {

    @Expose
    @SerializedName("lon")
    private long longitude;
    @Expose
    @SerializedName("lat")
    private long latitude;

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordination that = (Coordination) o;

        if (longitude != that.longitude) return false;
        return latitude == that.latitude;
    }

    @Override
    public int hashCode() {
        int result = (int) (longitude ^ (longitude >>> 32));
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        return result;
    }
}
