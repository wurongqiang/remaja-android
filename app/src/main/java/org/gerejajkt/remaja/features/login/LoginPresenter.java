package org.gerejajkt.remaja.features.login;

import android.util.Patterns;

import org.gerejajkt.remaja.custom.ResolvedSingleObserver;
import org.gerejajkt.remaja.domain.manageUser.ManageUser;
import org.gerejajkt.remaja.domain.viewparam.UserViewParam;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by huteri on 4/25/17.
 */

class LoginPresenter extends BasePresenter<LoginView> {

    private ManageUser manageUser;
    private BaseSchedulerProvider scheduler;

    @Inject
    public LoginPresenter(ManageUser manageUser, BaseSchedulerProvider schedulerProvider) {
        this.manageUser = manageUser;
        this.scheduler = schedulerProvider;
    }

    public void tapBtnLogin(String email, String password) {

        if(!isViewAttached())
            return;

        if(email.trim().length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            getView().showInvalidEmail();
            return;
        } else if(password.trim().length() == 0) {
            getView().showInvalidPassword();
            return;
        }

        getView().showLoadingBar();

        manageUser.login(email, password)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(new ResolvedSingleObserver<UserViewParam>(getView().getResolution()) {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);

                        if (!isViewAttached())
                            d.dispose();
                    }

                    @Override
                    public void onSuccess(@NonNull UserViewParam o) {
                        super.onSuccess(o);

                        getView().hideLoadingBar();
                        getView().navigateToMainActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                        getView().hideLoadingBar();
                    }
                });

    }

}
