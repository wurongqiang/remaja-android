package org.gerejajkt.remaja.features.attendance;

import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;
import org.gerejajkt.remaja.features.base.BaseView;

import java.util.List;

/**
 * Created by huteri on 5/2/17.
 */

interface AttendanceView extends BaseView {

    void showLoadingBar();

    void showEmptyState();

    void hideLoadingBar();

    void showAttendances(List<AttendanceViewParam> o);

    void navigateToQRCodeScannerActivity();
}
