package org.gerejajkt.remaja.domain.addAttendance;

import io.reactivex.Single;

/**
 * Created by wurongqiang on 5/6/17.
 */

public interface AddAttendance {
    Single<String> addAttendance(int sessionId);
}
