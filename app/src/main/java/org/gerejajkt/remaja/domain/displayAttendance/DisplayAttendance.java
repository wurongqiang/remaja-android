package org.gerejajkt.remaja.domain.displayAttendance;

import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by huteri on 5/3/17.
 */

public interface DisplayAttendance {
    Observable<List<AttendanceViewParam>> getAttendances();
}
