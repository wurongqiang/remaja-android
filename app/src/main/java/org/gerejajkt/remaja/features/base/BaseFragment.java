package org.gerejajkt.remaja.features.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gerejajkt.remaja.custom.resolution.Resolution;
import org.gerejajkt.remaja.utils.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by huteri on 4/27/17.
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    @LayoutRes
    public abstract int getLayoutResource();

    protected abstract void inject();

    @Inject
    Navigator navigator;

    @Override
    public Resolution getResolution() {
        return ((BaseActivity) getActivity()).getResolution();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
