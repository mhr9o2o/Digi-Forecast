package digi.mhr.digiforecast;

import android.app.Application;

import digi.mhr.digiforecast.data.DataFactory;
import io.realm.Realm;

/**
 * Created by mohammad on 1/16/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        DataFactory.init(this);
    }
}
