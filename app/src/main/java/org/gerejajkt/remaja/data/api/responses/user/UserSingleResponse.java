package org.gerejajkt.remaja.data.api.responses.user;

import com.google.gson.annotations.SerializedName;

import org.gerejajkt.remaja.model.User;

/**
 * Created by huteri on 4/26/17.
 */

public class UserSingleResponse {

    @SerializedName("saint")
    public UserResponse user;

    public String token;

    public User toUser() {

        User data = new User();
        data.setId(user.id);
        data.setName(user.name);
        data.setEmail(user.email);
        data.setHall(user.hall);
        data.setPhone(user.phone);
        data.setGender(user.gender);

        data.setToken(token);

        return data;
    }
}
