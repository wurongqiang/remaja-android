package org.gerejajkt.remaja.data.api.user;

import org.gerejajkt.remaja.model.User;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by huteri on 4/26/17.
 */

public interface UserApi {
    Single<User> login(String email, String password);
    Completable logout();
    Completable changePassword(String password);
    Completable editProfile(String name, String phone, String hall);
}
