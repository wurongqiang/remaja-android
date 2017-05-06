package org.gerejajkt.remaja.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import org.gerejajkt.remaja.features.login.LoginActivity;
import org.gerejajkt.remaja.features.main.MainActivity;
import org.gerejajkt.remaja.features.qrcodeScanner.QRCodeScannerActivity;

import javax.inject.Inject;

/**
 * Created by huteri on 4/25/17.
 */

public class Navigator {

    public static int QR_CODE_SCANNED = 1;

    @Inject
    public Navigator() {
    }

    public void navigateToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void navigateToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void navigateToQRCodeScannerActivity(Context context) {
        Intent intent = new Intent(context, QRCodeScannerActivity.class);
        ((FragmentActivity)context).startActivityForResult(intent, QR_CODE_SCANNED);
    }
}
