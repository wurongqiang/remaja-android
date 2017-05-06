package org.gerejajkt.remaja.data.api;

import org.gerejajkt.remaja.data.api.responses.attendance.AttendanceListResponse;
import org.gerejajkt.remaja.data.api.responses.user.UserSingleResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by huteri on 4/25/17.
 */

public interface ApiService {

    //* Manage User *//

    @POST("login")
    @FormUrlEncoded
    Single<UserSingleResponse> login(@Field("username") String email, @Field("password") String password);

    @POST("logout")
    Completable logout();


    //* Attendance *//

    @GET("attendances")
    Single<AttendanceListResponse> getAttendances();

    @POST("attendances")
    @FormUrlEncoded
    Single<Void> addAttendance(@Field("session_id") int sessionId);
}
