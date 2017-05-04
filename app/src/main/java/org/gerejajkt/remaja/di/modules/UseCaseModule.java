package org.gerejajkt.remaja.di.modules;

import org.gerejajkt.remaja.data.api.UserApi;
import org.gerejajkt.remaja.data.dao.UserDao;
import org.gerejajkt.remaja.domain.ManageUser;
import org.gerejajkt.remaja.domain.ManageUserImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/26/17.
 */

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    ManageUser provideManageUser(UserApi userApi, UserDao userDao) {
        return ManageUserImpl.getInstance(userApi, userDao);
    }

}
