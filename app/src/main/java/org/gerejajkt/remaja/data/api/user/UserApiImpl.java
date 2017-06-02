package org.gerejajkt.remaja.data.api.user;

import org.gerejajkt.remaja.data.api.ApiService;
import org.gerejajkt.remaja.model.User;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by huteri on 4/26/17.
 */

public class UserApiImpl implements UserApi {

    private final ApiService apiService;

    public UserApiImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<User> login(String email, String password) {
        return apiService.login(email, password).map(u -> u.toUser());
    }

    @Override
    public Completable logout() {
        return apiService.logout();
    }

    @Override
    public Completable changePassword(String password) {
        return apiService.changePassword(password);
    }
}
