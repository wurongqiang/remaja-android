package org.gerejajkt.remaja.features.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.features.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huteri on 4/27/17.
 */

public class ProfileFragment extends BaseFragment implements ProfileView {

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Inject
    ProfilePresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void inject() {
        ((BaseApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

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

    @Override
    public void showName(String name) {
        tvName.setText(name);
    }

    @Override
    public void showEmail(String email) {
        tvEmail.setText(email);
    }

    @Override
    public void showLoadingBar() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Logging out...");
        progressDialog.show();
    }

    @Override
    public void hideLoadingBar() {

        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public void navigateToLoginActivity() {
        getNavigator().navigateToLoginActivity(getActivity());
        getActivity().finish();
    }

    @OnClick(R.id.layout_logout)
    void tapLogout() {
        presenter.tapLogout();
    }
}
