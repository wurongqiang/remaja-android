package org.gerejajkt.remaja;

import android.app.Application;

import org.gerejajkt.remaja.di.components.ApplicationComponent;
import org.gerejajkt.remaja.di.components.DaggerApplicationComponent;
import org.gerejajkt.remaja.di.modules.AppModule;
import org.gerejajkt.remaja.di.modules.RetrofitModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by huteri on 4/25/17.
 */

public class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;
    private String BASE_URL = "http://localhost:3000/api/v1/";

    @Override
    public void onCreate() {
        super.onCreate();

        initInjector();
        initRealm();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule(BASE_URL))
                .build();

        applicationComponent.inject(this);
    }

    private void initRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("remaja-realm")
                .schemaVersion(1)
                .migration(new DatabaseMigration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
