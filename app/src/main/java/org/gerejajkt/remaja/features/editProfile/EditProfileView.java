package org.gerejajkt.remaja.features.editProfile;

import org.gerejajkt.remaja.domain.viewparam.UserViewParam;
import org.gerejajkt.remaja.features.base.BaseView;

/**
 * Created by wurongqiang on 6/2/17.
 */

public interface EditProfileView extends BaseView {

    void showLoadingBar();

    void hideLoadingBar();

    void showEmptyNameAlert();

    void showEmptyPhoneAlert();

    void showEmptyHallAlert();

    void close();

    void initializeForm(UserViewParam userViewParam);

}
