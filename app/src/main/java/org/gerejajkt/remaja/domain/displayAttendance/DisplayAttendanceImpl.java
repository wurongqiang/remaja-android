package org.gerejajkt.remaja.domain.displayAttendance;

import org.gerejajkt.remaja.data.api.attendance.AttendanceApi;
import org.gerejajkt.remaja.data.dao.attendance.AttendanceDao;
import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;
import org.gerejajkt.remaja.model.Attendance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huteri on 5/3/17.
 */

public class DisplayAttendanceImpl implements DisplayAttendance {

    private static DisplayAttendanceImpl instance;
    private final AttendanceDao attendanceDao;
    private final AttendanceApi attendanceApi;

    public static DisplayAttendanceImpl getInstance(AttendanceDao attendanceDao, AttendanceApi attendanceApi) {

        if(instance == null) {
            instance = new DisplayAttendanceImpl(attendanceDao, attendanceApi);
        }

        return instance;
    }

    public DisplayAttendanceImpl(AttendanceDao attendanceDao, AttendanceApi attendanceApi) {
        this.attendanceApi = attendanceApi;
        this.attendanceDao = attendanceDao;
    }

    @Override
    public Observable<List<AttendanceViewParam>> getAttendances() {

        Observable<List<Attendance>> disk = Observable.just(attendanceDao.getAttendances()).onErrorResumeNext(Observable.empty());

        Single<List<Attendance>> network = attendanceApi.getAttendances().observeOn(Schedulers.computation()).map(attendances -> {
            attendanceDao.deleteAll();
            attendanceDao.saveAttendances(attendances);
            return attendanceDao.getAttendances();
        });

        return Observable.concat(disk, network.toObservable()).filter(data -> data != null && !data.isEmpty()).map(AttendanceViewParam::create);
    }
}
