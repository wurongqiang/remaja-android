package org.gerejajkt.remaja.features.profile;

import org.gerejajkt.remaja.features.base.BaseView;

/**
 * Created by huteri on 4/27/17.
 */

interface ProfileView extends BaseView {
    void showName(String gender, String name);

    void showEmail(String email);

    void showHall(String hall);

    void showPhone(String phone);

    void showLoadingBar();

    void hideLoadingBar();

    void navigateToLoginActivity();

    void openChangePasswordActivity();

    void openEditProfileActivity();
}
