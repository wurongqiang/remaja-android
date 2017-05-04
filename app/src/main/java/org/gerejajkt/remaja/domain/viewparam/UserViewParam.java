package org.gerejajkt.remaja.domain.viewparam;

import org.gerejajkt.remaja.model.User;

/**
 * Created by huteri on 4/26/17.
 */

public class UserViewParam {

    private String name, email, profilePicture;

    public static UserViewParam create(User user) {
        UserViewParam userViewParam = new UserViewParam();
        userViewParam.name = user.getName();
        userViewParam.email = user.getEmail();
        userViewParam.profilePicture = user.getProfilePicture();

        return userViewParam;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
