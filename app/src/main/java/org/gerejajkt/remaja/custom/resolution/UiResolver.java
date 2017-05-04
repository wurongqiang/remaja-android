package org.gerejajkt.remaja.custom.resolution;

import android.widget.Toast;

import org.gerejajkt.remaja.features.base.BaseActivity;

/**
 * Created by huteri on 1/9/17.
 */
public class UiResolver {

    private final BaseActivity baseActivity;

    public UiResolver(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void showMesssage(String message) {
        Toast.makeText(baseActivity, message, Toast.LENGTH_SHORT).show();
    }

    public void showConnectionError() {
        Toast.makeText(baseActivity, "Please check your internet connection", Toast.LENGTH_SHORT).show();
    }
}
