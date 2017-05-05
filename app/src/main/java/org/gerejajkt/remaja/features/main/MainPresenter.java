package org.gerejajkt.remaja.features.main;

import android.view.MenuItem;

import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.domain.manageUser.ManageUser;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

/**
 * Created by huteri on 4/25/17.
 */

class MainPresenter extends BasePresenter<MainView> {

    private final ManageUser manageUser;
    private final BaseSchedulerProvider scheduler;

    @Inject
    public MainPresenter(ManageUser manageUser, BaseSchedulerProvider schedulerProvider) {
        this.manageUser = manageUser;
        this.scheduler = schedulerProvider;
    }

    @Override
    public void onCreateView(MainView view) {
        super.onCreateView(view);

        if(!manageUser.isLoggedIn()) {
            getView().navigateToLoginActivity();
        }
    }

    public void selectNavigationItem(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_attendance:
                getView().showAttendanceFragment();
                break;
            case R.id.action_me:
                getView().showProfileFragment();
                break;
        }
    }
}
