package org.gerejajkt.remaja.data.api.attendance;

import org.gerejajkt.remaja.data.api.ApiService;
import org.gerejajkt.remaja.data.api.responses.attendance.AttendanceResponse;
import org.gerejajkt.remaja.model.Attendance;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by huteri on 5/3/17.
 */

public class AttendanceApiImpl implements AttendanceApi {

    private final ApiService apiService;

    public AttendanceApiImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Attendance>> getAttendances() {
        return apiService.getAttendances().map(attendanceListResponse -> AttendanceResponse.toAttendances(attendanceListResponse.attendances));
    }

    @Override
    public Single<Void> addAttendance(int sessionId) {
        return apiService.addAttendance(sessionId);
    }
}
