package org.gerejajkt.remaja.features.changePassword;

import org.gerejajkt.remaja.features.base.BaseView;

/**
 * Created by wurongqiang on 6/2/17.
 */

public interface ChangePasswordView extends BaseView {

    void showLoadingBar();

    void hideLoadingBar();

    void showEmptyNewPasswordAlert();

    void showEmptyConfirmPasswordAlert();

    void showWrongConfirmationPasswordAlert();

    void close();
}
