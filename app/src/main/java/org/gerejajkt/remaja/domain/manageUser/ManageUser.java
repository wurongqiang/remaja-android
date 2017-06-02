package org.gerejajkt.remaja.domain.manageUser;

import org.gerejajkt.remaja.domain.viewparam.UserViewParam;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by huteri on 4/25/17.
 */

public interface ManageUser {

    Single<UserViewParam> login(String email, String password);
    Single<UserViewParam> getUser();
    Completable logout();
    Completable changePassword(String password);

    boolean isLoggedIn();
    String getToken();
}
