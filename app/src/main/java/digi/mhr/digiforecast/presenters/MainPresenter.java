package digi.mhr.digiforecast.presenters;

/**
 * Created by mohammad on 1/19/18.
 */

public interface MainPresenter {

    void onPermissionApproved();
    void onPermissionDenied();
    void onResume();
    void onRefresh();

}
