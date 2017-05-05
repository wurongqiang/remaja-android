package org.gerejajkt.remaja.data.dao.attendance;

import com.google.gson.Gson;

import org.gerejajkt.remaja.model.Attendance;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by huteri on 5/3/17.
 */

public class AttendanceDaoImpl implements AttendanceDao {

    private final Gson gson;

    public AttendanceDaoImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<Attendance> getAttendances() {

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Attendance> allAttendancesQuery = realm.where(Attendance.class);

        RealmResults<Attendance> attendances = (RealmResults<Attendance>) allAttendancesQuery.findAllSorted("meetingDate", Sort.DESCENDING);

        List<Attendance> Attendances1 = realm.copyFromRealm(attendances);

        realm.close();
        return Attendances1;
    }

    @Override
    public void saveAttendances(List<Attendance> attendances) {

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> {

            String json = gson.toJson(attendances);
            realm1.createOrUpdateAllFromJson(Attendance.class, json);
        });

        realm.close();

    }

    @Override
    public void deleteAll() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> {

            realm1.where(Attendance.class).findAll().deleteAllFromRealm();
        });

        realm.close();
    }
}
