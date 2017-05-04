package org.gerejajkt.remaja.custom;


import org.gerejajkt.remaja.custom.resolution.Resolution;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by huteri on 1/9/17.
 */

public class ResolvedObserver<T> implements Observer<T> {

    private Resolution resolution;


    public ResolvedObserver(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public void onError(Throwable e) {
        try {
            new ResolvedOnErrorAction(resolution).accept(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(T o) {
    }

}
