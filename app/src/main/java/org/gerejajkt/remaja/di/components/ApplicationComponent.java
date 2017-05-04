package org.gerejajkt.remaja.di.components;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.di.modules.ApiModule;
import org.gerejajkt.remaja.di.modules.AppModule;
import org.gerejajkt.remaja.di.modules.DaoModule;
import org.gerejajkt.remaja.di.modules.RetrofitModule;
import org.gerejajkt.remaja.di.modules.UseCaseModule;
import org.gerejajkt.remaja.features.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by huteri on 4/25/17.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        RetrofitModule.class,
        ApiModule.class,
        UseCaseModule.class,
        DaoModule.class
})
public interface ApplicationComponent {
    void inject(BaseApplication baseApplication);

    void inject(LoginActivity loginActivity);
}
