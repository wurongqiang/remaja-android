package org.gerejajkt.remaja.data.dao.attendance;

import org.gerejajkt.remaja.model.Attendance;

import java.util.List;

/**
 * Created by huteri on 5/3/17.
 */

public interface AttendanceDao {

    List<Attendance> getAttendances();

    void saveAttendances(List<Attendance> attendances);

    void deleteAll();
}
