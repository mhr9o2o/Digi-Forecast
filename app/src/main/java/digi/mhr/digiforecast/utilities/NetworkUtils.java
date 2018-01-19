package digi.mhr.digiforecast.utilities;

import java.util.Iterator;
import java.util.Map;

import digi.mhr.digiforecast.network.api.APIs;

/**
 * Created by mohammad on 1/19/18.
 */

public class NetworkUtils {

    public static String createRequestURL(String apiKey, Map<String, String> params) {
        String url = APIs.BASE_URL;
        StringBuilder builder = new StringBuilder(url);
        builder.append(apiKey);

        int i = 1;
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if(i == 1) {
                builder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
            } else {
                builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            iterator.remove(); // avoids a ConcurrentModificationException
            i++;
        }

        builder.append("&").append(APIs.APP_ID_KEY).append("=").append(APIs.APP_ID);
        builder.append("&").append(APIs.UNITS_KEY).append("=").append(APIs.UNITS_VALUE);

        url = builder.toString();

        return url;
    }

}
