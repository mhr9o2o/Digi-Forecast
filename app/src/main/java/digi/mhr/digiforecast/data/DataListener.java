package digi.mhr.digiforecast.data;

import com.android.volley.VolleyError;

/**
 * Created by mohammad on 1/19/18.
 */

public interface DataListener<T> {

     void onDataReceived(T data);
     void onError(String error);

}
