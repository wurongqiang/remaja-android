package org.gerejajkt.remaja.features.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.features.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huteri on 4/25/17.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    @Inject
    LoginPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
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
        progressDialog.setMessage("Logging in...");
        progressDialog.show();
    }

    @Override
    public void hideLoadingBar() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public void navigateToMainActivity() {
        getNavigator().navigateToMainActivity(this);
        finish();
    }

    @Override
    public void showInvalidEmail() {
        etEmail.setError("Please enter a valid email address");
    }

    @Override
    public void showInvalidPassword() {
        etPassword.setError("Please enter a password");
    }

    @OnClick(R.id.btn_login)
    void tapBtnLogin() {
        presenter.tapBtnLogin(etEmail.getText().toString(), etPassword.getText().toString());
    }

}
