package org.gerejajkt.remaja.di.modules;

import org.gerejajkt.remaja.data.api.attendance.AttendanceApi;
import org.gerejajkt.remaja.data.api.user.UserApi;
import org.gerejajkt.remaja.data.dao.attendance.AttendanceDao;
import org.gerejajkt.remaja.data.dao.user.UserDao;
import org.gerejajkt.remaja.domain.displayAttendance.DisplayAttendance;
import org.gerejajkt.remaja.domain.displayAttendance.DisplayAttendanceImpl;
import org.gerejajkt.remaja.domain.manageUser.ManageUser;
import org.gerejajkt.remaja.domain.manageUser.ManageUserImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/26/17.
 */

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    ManageUser provideManageUser(UserApi userApi, UserDao userDao, AttendanceDao attendanceDao) {
        return ManageUserImpl.getInstance(userApi, userDao, attendanceDao);
    }

    @Provides
    @Singleton
    DisplayAttendance provideDisplayAttendance(AttendanceDao assignmentDao, AttendanceApi attendanceApi) {
        return DisplayAttendanceImpl.getInstance(assignmentDao, attendanceApi);
    }
}
