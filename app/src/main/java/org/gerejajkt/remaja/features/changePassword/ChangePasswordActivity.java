package org.gerejajkt.remaja.features.changePassword;

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

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView {

    @BindView(R.id.et_new_password)
    EditText etNewPassword;

    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @Inject
    ChangePasswordPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_change_password;
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
    public void showEmptyNewPasswordAlert() {
        etNewPassword.setError("Please fill your new password");
    }

    @Override
    public void showEmptyConfirmPasswordAlert() {
        etConfirmPassword.setError("Please confirm your new password");
    }

    @Override
    public void showWrongConfirmationPasswordAlert() {
        etConfirmPassword.setError("Password Confirmation doesn't match");
    }

    @OnClick(R.id.btn_save)
    void tapBtnSave() {
        presenter.tabBtnSave(etNewPassword.getText().toString(), etConfirmPassword.getText().toString());
    }

}
