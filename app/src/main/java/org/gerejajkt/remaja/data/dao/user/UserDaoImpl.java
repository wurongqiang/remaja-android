package org.gerejajkt.remaja.data.dao.user;

import org.gerejajkt.remaja.model.User;

import io.realm.Realm;

/**
 * Created by huteri on 4/26/17.
 */

public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser(User u) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(u));
        realm.close();
    }

    @Override
    public User getUser() {

        Realm realm = Realm.getDefaultInstance();
        User first = realm.where(User.class).findFirst();

        User user = null;

        if (first != null) {
            user = realm.copyFromRealm(first);
        }

        realm.close();

        return user;
    }

    @Override
    public void deleteUser() {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.where(User.class).findAll().deleteAllFromRealm());

        realm.close();
    }
}
