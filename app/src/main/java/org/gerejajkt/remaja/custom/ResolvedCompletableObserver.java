package org.gerejajkt.remaja.custom;

import org.gerejajkt.remaja.custom.resolution.Resolution;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by huteri on 4/27/17.
 */

public class ResolvedCompletableObserver implements CompletableObserver {
    private Resolution resolution;
    private Disposable disposable;

    public ResolvedCompletableObserver(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;

    }

    @Override
    public void onComplete() {

        if(resolution == null)
            disposable.dispose();

    }

    @Override
    public void onError(@NonNull Throwable e) {
        try {
            new ResolvedOnErrorAction(resolution).accept(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
