package org.gerejajkt.remaja.features.attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;
import org.gerejajkt.remaja.features.adapter.AttendanceAdapter;
import org.gerejajkt.remaja.features.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huteri on 5/2/17.
 */

public class AttendanceFragment extends BaseFragment implements AttendanceView {

    @BindView(R.id.empty_state)
    ViewGroup emptyState;

    @BindView(R.id.rv_content)
    RecyclerView rvMain;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Inject
    AttendancePresenter presenter;

    private AttendanceAdapter attendanceAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_attendance;
    }

    @Override
    protected void inject() {
        ((BaseApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        initRvMain();

        presenter.onCreateView(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onAttachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.onDetachView();
    }

    @OnClick(R.id.fab)
    void tapAddAttendanceButton() {
        presenter.tapAddAttendanceButton();
    }


    @Override
    public void showLoadingBar() {
        pbLoading.setVisibility(View.VISIBLE);
        rvMain.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyState() {
        rvMain.setVisibility(View.GONE);
        emptyState.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingBar() {
        pbLoading.setVisibility(View.GONE);
        rvMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAttendances(List<AttendanceViewParam> o) {
        attendanceAdapter.setData(o);
    }

    private void initRvMain() {
        attendanceAdapter = new AttendanceAdapter(getActivity());

        rvMain.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvMain.setAdapter(attendanceAdapter);
    }
}
