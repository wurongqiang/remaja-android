package org.gerejajkt.remaja.data.api;

import org.gerejajkt.remaja.data.api.responses.UserSingleResponse;

import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by huteri on 4/25/17.
 */

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Single<UserSingleResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("logout")
    @FormUrlEncoded
    Completable logout(@Field("device_id") String deviceId);

}
