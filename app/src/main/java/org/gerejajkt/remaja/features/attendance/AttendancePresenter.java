package org.gerejajkt.remaja.features.attendance;

import org.gerejajkt.remaja.custom.ResolvedObserver;
import org.gerejajkt.remaja.custom.ResolvedSingleObserver;
import org.gerejajkt.remaja.domain.addAttendance.AddAttendance;
import org.gerejajkt.remaja.domain.displayAttendance.DisplayAttendance;
import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by huteri on 5/2/17.
 */

class AttendancePresenter extends BasePresenter<AttendanceView> {
    private final AddAttendance addAttendance;
    private final DisplayAttendance displayAttendance;
    private final BaseSchedulerProvider scheduler;

    public String valueScannedFromQRCode = "";

    @Inject
    public AttendancePresenter(DisplayAttendance displayAttendance, AddAttendance addAttendance, BaseSchedulerProvider schedulerProvider) {
        this.displayAttendance = displayAttendance;
        this.addAttendance = addAttendance;
        this.scheduler = schedulerProvider;
    }

    @Override
    public void onCreateView(AttendanceView view) {
        super.onCreateView(view);

        loadAttendances();
    }

    @Override
    public void onAttachView(AttendanceView view) {
        super.onAttachView(view);

        if (!valueScannedFromQRCode.isEmpty()) {
            onValueScannedFromQRCode(valueScannedFromQRCode);
            valueScannedFromQRCode = "";
        }
    }

    public void tapAddAttendanceButton() {
        getView().navigateToQRCodeScannerActivity();
    }

    private void onValueScannedFromQRCode(String value) {
        try {
            int sessionId = Integer.parseInt(value);
            addAttendance(sessionId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void addAttendance(int sessionId) {
        getView().showLoadingBar();
        addAttendance.addAttendance(sessionId)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(new ResolvedSingleObserver<String>(getView().getResolution()) {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);

                        if (!isViewAttached())
                            d.dispose();
                    }

                    @Override
                    public void onSuccess(@NonNull String message) {
                        super.onSuccess(message);

                        getView().hideLoadingBar();
                        loadAttendances();
                        getView().showSnackbar(message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                        getView().hideLoadingBar();
                    }
                });
    }

    private void loadAttendances() {
        getView().showLoadingBar();
        displayAttendance.getAttendances()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui(), true)
                .subscribe(new ResolvedObserver<List<AttendanceViewParam>>(getView().getResolution()) {

                    public List<AttendanceViewParam> attendances;

                    @Override
                    public void onComplete() {
                        super.onComplete();

                        if (!isViewAttached())
                            return;

                        getView().hideLoadingBar();

                        if (attendances == null || attendances.size() == 0) {
                            getView().showEmptyState();
                        }
                    }

                    @Override
                    public void onNext(List<AttendanceViewParam> attendanceViewParams) {

                        if (!isViewAttached())
                            return;

                        super.onNext(attendanceViewParams);

                        getView().hideLoadingBar();
                        attendances = attendanceViewParams;
                        getView().showAttendances(attendanceViewParams);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!isViewAttached())
                            return;

                        super.onError(e);

                        getView().hideLoadingBar();
                    }
                });
    }

}
