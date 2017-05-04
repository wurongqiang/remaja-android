package org.gerejajkt.remaja.data.api.responses;

import org.gerejajkt.remaja.model.User;

/**
 * Created by huteri on 4/26/17.
 */

public class UserSingleResponse {

    public UserResponse user;
    public String token;

    public User toUser() {

        User data = new User();
        data.setName(user.name);
        data.setEmail(user.email);
        data.setToken(token);
        data.setId(user.id);
        data.setNationality(user.nationality);
        data.setPhoneCode(user.phoneCode);
        data.setProfilePicture(user.profilePicture);

        return data;
    }
}
