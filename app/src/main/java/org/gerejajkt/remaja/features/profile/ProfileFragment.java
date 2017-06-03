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

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_hall)
    TextView tvHall;

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
    public void showName(String gender, String name) {
        tvName.setText((gender.equals("male") ? "Sdra. " : "Sdri. ") + name);
    }

    @Override
    public void showEmail(String email) {
        tvEmail.setText(email);
    }

    @Override
    public void showHall(String hall) {
        tvHall.setText("Hall: " + (hall == null || hall.isEmpty() ? "-" : hall));
    }

    @Override
    public void showPhone(String phone) {
        tvPhone.setText(phone == null || phone.isEmpty() ? "no phone number" : phone);
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

    @Override
    public void openChangePasswordActivity() {
        getNavigator().openChangePasswordActivity(getActivity());
    }

    @Override
    public void openEditProfileActivity() {
        getNavigator().openEditProfileActivity(getActivity());
    }

    @OnClick(R.id.layout_logout)
    void tapLogout() {
        presenter.tapLogout();
    }

    @OnClick(R.id.layout_change_password)
    void tapChangePassword() {
        presenter.tapChangePassword();
    }

    @OnClick(R.id.layout_edit_profile)
    void tapEditProfile() {
        presenter.tapEditProfile();
    }
}
