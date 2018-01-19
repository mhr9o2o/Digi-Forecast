package digi.mhr.digiforecast.utilities;

/**
 * Created by mohammad on 1/19/18.
 */

public class TimeUtils {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getTimeAgo(long ts) {
        ts = checkSecondsOrMillisecond(ts);

        long now = System.currentTimeMillis();
        if (ts > now || ts <= 0) {
            return null;
        }

        final long diff = now - ts;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    static long checkSecondsOrMillisecond(long ts) {
        if (ts < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            ts *= 1000;
        }
        return ts;
    }



}
