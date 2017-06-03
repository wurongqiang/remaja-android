package org.gerejajkt.remaja.domain.viewparam;

import org.gerejajkt.remaja.model.User;

/**
 * Created by huteri on 4/26/17.
 */

public class UserViewParam {

    private String name, email, phone, gender, hall;

    public static UserViewParam create(User user) {
        UserViewParam userViewParam = new UserViewParam();
        userViewParam.name = user.getName();
        userViewParam.email = user.getEmail();
        userViewParam.hall = user.getHall();
        userViewParam.phone = user.getPhone();
        userViewParam.gender = user.getGender();

        return userViewParam;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getHall() {
        return hall;
    }

    public String getGender() {
        return gender;
    }
}
