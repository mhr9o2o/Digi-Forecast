package digi.mhr.digiforecast.utilities.Location;

import android.location.Location;

/**
 * Created by mohammad on 1/19/18.
 */

public interface LocationTracker {

    interface LocationUpdateListener{
        void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime);
    }

    void start();
    void start(LocationUpdateListener update);

    void stop();

    boolean hasLocation();

    boolean hasPossiblyStaleLocation();

    Location getLocation();

    Location getPossiblyStaleLocation();

}
