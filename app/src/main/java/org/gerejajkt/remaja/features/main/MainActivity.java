package org.gerejajkt.remaja.features.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import org.gerejajkt.remaja.BaseApplication;
import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.features.attendance.AttendanceFragment;
import org.gerejajkt.remaja.features.base.BaseActivity;
import org.gerejajkt.remaja.features.profile.ProfileFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Inject
    MainPresenter presenter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        ((BaseApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initStartFragment();

        presenter.onCreateView(this);

        bottomNavigation.setOnNavigationItemSelectedListener(this);
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
    public void navigateToLoginActivity() {
        getNavigator().navigateToLoginActivity(this);
        finish();
    }

    @Override
    public void showProfileFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
    }

    @Override
    public void showAttendanceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendanceFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        presenter.selectNavigationItem(item);
        return true;
    }

    private void initStartFragment() {

        if (getSupportFragmentManager().findFragmentByTag("fragment") == null)
            getSupportFragmentManager().beginTransaction().add(R.id.container, new AttendanceFragment(), "fragment").commit();

    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag("fragment");
    }
}
