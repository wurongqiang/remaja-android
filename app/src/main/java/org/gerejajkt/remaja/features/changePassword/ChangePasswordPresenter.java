package org.gerejajkt.remaja.features.changePassword;

import android.support.annotation.NonNull;

import org.gerejajkt.remaja.custom.ResolvedCompletableObserver;
import org.gerejajkt.remaja.domain.manageUser.ManageUser;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

/**
 * Created by wurongqiang on 6/2/17.
 */

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {

    private ManageUser manageUser;
    private BaseSchedulerProvider scheduler;

    @Inject
    public ChangePasswordPresenter(ManageUser manageUser, BaseSchedulerProvider schedulerProvider) {
        this.manageUser = manageUser;
        this.scheduler = schedulerProvider;
    }

    public void tabBtnSave(String password, String confirmPassword) {
        if (!isViewAttached())
            return;

        if (password.trim().length() == 0) {
            getView().showEmptyNewPasswordAlert();
            return;
        } else if (confirmPassword.trim().length() == 0) {
            getView().showEmptyConfirmPasswordAlert();
            return;
        } else if (!password.equals(confirmPassword)) {
            getView().showWrongConfirmationPasswordAlert();
            return;
        }

        getView().showLoadingBar();

        manageUser.changePassword(password)
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
