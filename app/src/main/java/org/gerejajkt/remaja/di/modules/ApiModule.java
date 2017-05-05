package org.gerejajkt.remaja.di.modules;

import org.gerejajkt.remaja.data.api.ApiService;
import org.gerejajkt.remaja.data.api.attendance.AttendanceApi;
import org.gerejajkt.remaja.data.api.attendance.AttendanceApiImpl;
import org.gerejajkt.remaja.data.api.user.UserApi;
import org.gerejajkt.remaja.data.api.user.UserApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/26/17.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    UserApi provideUserApi(ApiService apiService) {
        return new UserApiImpl(apiService);
    }

    @Provides
    AttendanceApi provideAttendanceApi(ApiService apiService) { return new AttendanceApiImpl(apiService); }

}
