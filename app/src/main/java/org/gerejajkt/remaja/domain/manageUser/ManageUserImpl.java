package org.gerejajkt.remaja.domain.manageUser;

import org.gerejajkt.remaja.data.api.user.UserApi;
import org.gerejajkt.remaja.data.dao.attendance.AttendanceDao;
import org.gerejajkt.remaja.data.dao.user.UserDao;
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
    private final AttendanceDao attendanceDao;
    private User userInMemory;

    public static ManageUserImpl getInstance(UserApi userApi, UserDao userDao, AttendanceDao attendaceDao) {

        if (sInstance == null) {
            sInstance = new ManageUserImpl(userApi, userDao, attendaceDao);
        }
        return sInstance;
    }

    private ManageUserImpl(UserApi userApi, UserDao userDao, AttendanceDao attendanceDao) {
        this.userApi = userApi;
        this.userDao = userDao;
        this.attendanceDao = attendanceDao;
    }

    @Override
    public Completable changePassword(String password) {
        return userApi.changePassword(password);
    }

    @Override
    public Completable editProfile(String name, String phone, String hall) {
        return userApi.editProfile(name, phone, hall);
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
    public Completable logout() {
        return userApi.logout().doOnComplete(() -> {
            attendanceDao.deleteAll();
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
