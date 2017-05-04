package org.gerejajkt.remaja.data.dao;

import org.gerejajkt.remaja.model.User;

/**
 * Created by huteri on 4/26/17.
 */

public interface UserDao {

    void saveUser(User u);

    User getUser();

    void deleteUser();

}
