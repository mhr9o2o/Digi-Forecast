package digi.mhr.digiforecast;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by mohammad on 1/16/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
