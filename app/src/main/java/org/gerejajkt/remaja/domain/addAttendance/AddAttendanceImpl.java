package org.gerejajkt.remaja.domain.addAttendance;

import org.gerejajkt.remaja.data.api.attendance.AttendanceApi;

import io.reactivex.Single;

/**
 * Created by wurongqiang on 5/6/17.
 */

public class AddAttendanceImpl implements AddAttendance {

    private static AddAttendanceImpl instance;
    private final AttendanceApi attendanceApi;

    public static AddAttendanceImpl getInstance(AttendanceApi attendanceApi) {

        if(instance == null) {
            instance = new AddAttendanceImpl(attendanceApi);
        }

        return instance;
    }

    public AddAttendanceImpl(AttendanceApi attendanceApi) {
        this.attendanceApi = attendanceApi;
    }

    @Override
    public Single<String> addAttendance(int sessionId) {
        return attendanceApi.addAttendance(sessionId);
    }
}
