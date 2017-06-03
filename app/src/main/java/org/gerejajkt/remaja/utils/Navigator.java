package org.gerejajkt.remaja.utils;

import android.content.Context;
import android.content.Intent;

import org.gerejajkt.remaja.features.changePassword.ChangePasswordActivity;
import org.gerejajkt.remaja.features.editProfile.EditProfileActivity;
import org.gerejajkt.remaja.features.login.LoginActivity;
import org.gerejajkt.remaja.features.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by huteri on 4/25/17.
 */

public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void navigateToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void openChangePasswordActivity(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    public void openEditProfileActivity(Context context) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        context.startActivity(intent);
    }
}
