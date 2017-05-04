package org.gerejajkt.remaja.di.modules;

import org.gerejajkt.remaja.data.dao.UserDao;
import org.gerejajkt.remaja.data.dao.UserDaoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/26/17.
 */

@Module
public class DaoModule {

    @Provides
    @Singleton
    UserDao provideUserDao() {
        return new UserDaoImpl();
    }

}
