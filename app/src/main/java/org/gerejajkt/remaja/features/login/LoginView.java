package org.gerejajkt.remaja.features.login;

import org.gerejajkt.remaja.features.base.BaseView;

/**
 * Created by huteri on 4/25/17.
 */

interface LoginView extends BaseView {
    void showLoadingBar();

    void hideLoadingBar();

    void navigateToMainActivity();

    void showInvalidEmail();

    void showInvalidPassword();
}
