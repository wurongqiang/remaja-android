package org.gerejajkt.remaja.data.api.attendance;

import org.gerejajkt.remaja.model.Attendance;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by huteri on 5/3/17.
 */

public interface AttendanceApi {

    Single<List<Attendance>> getAttendances();

}
