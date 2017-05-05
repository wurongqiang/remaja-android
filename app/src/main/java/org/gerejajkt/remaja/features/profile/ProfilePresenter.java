package org.gerejajkt.remaja.features.profile;

import org.gerejajkt.remaja.custom.ResolvedCompletableObserver;
import org.gerejajkt.remaja.domain.ManageUser;
import org.gerejajkt.remaja.domain.viewparam.UserViewParam;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by huteri on 4/27/17.
 */

class ProfilePresenter extends BasePresenter<ProfileView> {

    private final ManageUser manageUser;
    private final BaseSchedulerProvider scheduler;

    @Inject
    public ProfilePresenter(ManageUser manageUser, BaseSchedulerProvider schedulerProvider) {
        this.manageUser = manageUser;
        this.scheduler = schedulerProvider;
    }

    @Override
    public void onCreateView(ProfileView view) {
        super.onCreateView(view);

        UserViewParam userViewParam = manageUser.getUser().blockingGet();

        getView().showName(userViewParam.getName());
        getView().showEmail(userViewParam.getEmail());

    }

    public void tapLogout() {

        getView().showLoadingBar();

        manageUser.logout(null)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(new ResolvedCompletableObserver(getView().getResolution()) {
                    @Override
                    public void onComplete() {
                        if(!isViewAttached())
                            return;

                        super.onComplete();

                        getView().hideLoadingBar();
                        getView().navigateToLoginActivity();
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