package org.gerejajkt.remaja.features.attendance;

import org.gerejajkt.remaja.custom.ResolvedObserver;
import org.gerejajkt.remaja.domain.displayAttendance.DisplayAttendance;
import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;
import org.gerejajkt.remaja.features.base.BasePresenter;
import org.gerejajkt.remaja.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by huteri on 5/2/17.
 */

class AttendancePresenter extends BasePresenter<AttendanceView> {
    private final DisplayAttendance displayAttendance;
    private final BaseSchedulerProvider scheduler;

    @Inject
    public AttendancePresenter(DisplayAttendance displayAttendance, BaseSchedulerProvider schedulerProvider) {
        this.displayAttendance = displayAttendance;
        this.scheduler = schedulerProvider;
    }

    @Override
    public void onCreateView(AttendanceView view) {
        super.onCreateView(view);

        loadAttendances();
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
