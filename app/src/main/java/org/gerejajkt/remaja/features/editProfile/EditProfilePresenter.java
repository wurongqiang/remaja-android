package org.gerejajkt.remaja.features.editProfile;

import android.support.annotation.NonNull;

import org.gerejajkt.remaja.custom.ResolvedCompletableObserver;
import org.gerejajkt.remaja.domain.manageUser.ManageUser;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

/**
 * Created by wurongqiang on 6/2/17.
 */

public class EditProfilePresenter extends BasePresenter<EditProfileView> {

    private ManageUser manageUser;
    private BaseSchedulerProvider scheduler;

    @Inject
    public EditProfilePresenter(ManageUser manageUser, BaseSchedulerProvider schedulerProvider) {
        this.manageUser = manageUser;
        this.scheduler = schedulerProvider;
    }

    public void tabBtnSave(String name, String phone, String hall) {
        if(!isViewAttached())
            return;

        if (name.trim().length() == 0) {
            getView().showEmptyNameAlert();
            return;
        } else if (phone.trim().length() == 0) {
            getView().showEmptyPhoneAlert();
            return;
        } else if (hall.trim().length() == 0) {
            getView().showEmptyHallAlert();
            return;
        }

        getView().showLoadingBar();

        manageUser.editProfile(name, phone, hall)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(new ResolvedCompletableObserver(getView().getResolution()) {
                    @Override
                    public void onComplete() {
                        if(!isViewAttached())
                            return;

                        super.onComplete();

                        getView().hideLoadingBar();
                        getView().close();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        if(!isViewAttached())
                            return;

                        super.onError(e);

                        getView().hideLoadingBar();
                    }
                });
    }
}
