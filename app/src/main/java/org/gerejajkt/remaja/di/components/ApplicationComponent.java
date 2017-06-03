package org.gerejajkt.remaja.di.components;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.di.modules.ApiModule;
import org.gerejajkt.remaja.di.modules.AppModule;
import org.gerejajkt.remaja.di.modules.DaoModule;
import org.gerejajkt.remaja.di.modules.RetrofitModule;
import org.gerejajkt.remaja.di.modules.UseCaseModule;
import org.gerejajkt.remaja.features.attendance.AttendanceFragment;
import org.gerejajkt.remaja.features.changePassword.ChangePasswordActivity;
import org.gerejajkt.remaja.features.editProfile.EditProfileActivity;
import org.gerejajkt.remaja.features.login.LoginActivity;
import org.gerejajkt.remaja.features.main.MainActivity;
import org.gerejajkt.remaja.features.profile.ProfileFragment;

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

    void inject(MainActivity mainActivity);

    void inject(ProfileFragment profileFragment);

    void inject(AttendanceFragment attendanceFragment);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(EditProfileActivity editProfileActivity);
}
