package org.gerejajkt.remaja.di.modules;

import com.google.gson.Gson;

import org.gerejajkt.remaja.data.dao.attendance.AttendanceDao;
import org.gerejajkt.remaja.data.dao.attendance.AttendanceDaoImpl;
import org.gerejajkt.remaja.data.dao.user.UserDao;
import org.gerejajkt.remaja.data.dao.user.UserDaoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/26/17.
 */

@Module
public class DaoModule {

    @Provides
    @Singleton
    UserDao provideUserDao() {
        return new UserDaoImpl();
    }

    @Provides
    @Singleton
    AttendanceDao provideAttendanceDao(Gson gson) {
        return new AttendanceDaoImpl(gson);
    }
}
