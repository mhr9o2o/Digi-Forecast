package digi.mhr.digiforecast.presenters;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import digi.mhr.digiforecast.R;
import digi.mhr.digiforecast.models.Coordination;
import digi.mhr.digiforecast.network.responses.GetCurrentWeatherResponse;
import digi.mhr.digiforecast.views.MainView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mohammad on 1/20/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView mainView;
    @Mock
    Coordination coordination;

    private MainPresenterImp presenter;

    @Before
    public void init() throws Exception {
        presenter = new MainPresenterImp(mainView);
    }

    @Test
    public void checkIfErrorIsShownOnPermissionDenial() {
        presenter.onPermissionDenied();
        verify(mainView, times(1)).showError(R.string.location_permission_denied_message);
    }

    @Test
    public void checkIfViewIsConfiguredAfterPermissionApproval() {
        presenter.onPermissionApproved();
        verify(mainView, times(1)).configureViewAfterInitializing();
    }

    @Test
    public void checkIfLoadingIsShownAfterPermissionApproval() {
        presenter.onPermissionApproved();
        verify(mainView, times(1)).showLoading();
    }

    @Test
    public void checkIfViewIsSetAsInitialOnResumeWithoutPermission() {
        presenter.onResume(false);
        verify(mainView, times(1)).setInitialViewState();
    }

    @Test
    public void checkIfWithNullResponseNothingWillHappen() {
        presenter.initResponseOnView(null);
        verify(mainView).configureViewAfterInitializing();
    }

    @Test
    public void checkIfOnAnEmptyResponseNoMethodExceptRegionIsCalled() {
        GetCurrentWeatherResponse response = new GetCurrentWeatherResponse();
        presenter.initResponseOnView(response);
        verify(mainView, times(1)).setRegionInfoData("");
    }


}