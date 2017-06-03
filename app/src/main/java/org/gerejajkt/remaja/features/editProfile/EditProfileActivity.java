package org.gerejajkt.remaja.features.editProfile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.features.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wurongqiang on 6/3/17.
 */

public class EditProfileActivity extends BaseActivity implements EditProfileView {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_phone)
    EditText etPhone;

    @BindView(R.id.et_hall)
    EditText etHall;

    @Inject
    EditProfilePresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void inject() {
        ((BaseApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.onCreateView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onAttachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.onDetachView();
    }

    @Override
    public void showLoadingBar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();
    }

    @Override
    public void hideLoadingBar() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showEmptyHallAlert() {
        etHall.setError("Please fill your hall");
    }

    @Override
    public void showEmptyNameAlert() {
        etName.setError("Please fill your name");
    }

    @Override
    public void showEmptyPhoneAlert() {
        etPhone.setError("Please fill your phone");
    }

    @OnClick(R.id.btn_save)
    void tapBtnSave() {
        presenter.tabBtnSave(etName.getText().toString(), etPhone.getText().toString(), etHall.getText().toString());
    }

}

