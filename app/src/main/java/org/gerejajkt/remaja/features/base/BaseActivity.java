package org.gerejajkt.remaja.features.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.gerejajkt.remaja.custom.resolution.Resolution;
import org.gerejajkt.remaja.custom.resolution.UiResolution;
import org.gerejajkt.remaja.custom.resolution.UiResolver;
import org.gerejajkt.remaja.utils.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by huteri on 4/25/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Inject
    Navigator navigator;

    @LayoutRes
    public abstract int getLayoutResource();

    protected abstract void inject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        inject();
    }

    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public Resolution getResolution() {
        return new UiResolution(new UiResolver(this));
    }
}
