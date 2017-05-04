package org.gerejajkt.remaja.domain;

import org.gerejajkt.remaja.data.api.UserApi;
import org.gerejajkt.remaja.data.dao.UserDao;
import org.gerejajkt.remaja.domain.viewparam.UserViewParam;
import org.gerejajkt.remaja.model.User;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by huteri on 4/25/17.
 */

public class ManageUserImpl implements ManageUser {

    private static ManageUserImpl sInstance;
    private final UserDao userDao;
    private final UserApi userApi;
    private User userInMemory;

    public static ManageUserImpl getInstance(UserApi userApi, UserDao userDao) {

        if (sInstance == null) {
            sInstance = new ManageUserImpl(userApi, userDao);

        }
        return sInstance;
    }

    private ManageUserImpl(UserApi userApi, UserDao userDao) {
        this.userApi = userApi;
        this.userDao = userDao;
    }

    @Override
    public Single<UserViewParam> login(String email, String password) {
        return userApi.login(email, password).map(u -> {
            userDao.saveUser(u);
            userInMemory = u;

            return UserViewParam.create(userInMemory);
        });
    }

    @Override
    public Single<UserViewParam> getUser() {
        if (userInMemory == null)
            userInMemory = userDao.getUser();

        if (userInMemory == null)
            return null;

        return Single.just(UserViewParam.create(userInMemory));

    }

    @Override
    public Completable logout(String deviceId) {
        return userApi.logout(deviceId).doOnComplete(() -> {
            userDao.deleteUser();
            userInMemory = null;
        });
    }

    @Override
    public boolean isLoggedIn() {
        return getUser() != null;
    }

    @Override
    public String getToken() {
        return userInMemory != null ? userInMemory.getToken() : null;
    }
}
