package org.gerejajkt.remaja.di.modules;

import org.gerejajkt.remaja.data.api.ApiService;
import org.gerejajkt.remaja.data.api.UserApi;
import org.gerejajkt.remaja.data.api.UserApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/26/17.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    UserApi provideUserApi(ApiService apiService) {
        return new UserApiImpl(apiService);
    }
}
