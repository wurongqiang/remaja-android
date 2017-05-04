package org.gerejajkt.remaja.di.modules;

import android.app.Application;

import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;
import org.gerejajkt.remaja.utils.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huteri on 4/25/17.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }


    @Provides
    @Singleton
    BaseSchedulerProvider provideScheduler() {
        return new SchedulerProvider();
    }
}
