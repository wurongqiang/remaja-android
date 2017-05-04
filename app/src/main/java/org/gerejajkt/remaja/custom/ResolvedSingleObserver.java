package org.gerejajkt.remaja.custom;

import org.gerejajkt.remaja.custom.ResolvedOnErrorAction;
import org.gerejajkt.remaja.custom.resolution.Resolution;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.single.SingleObserveOn;

/**
 * Created by huteri on 4/26/17.
 */

public class ResolvedSingleObserver<T> implements SingleObserver<T>{

    private final Resolution resolution;

    public ResolvedSingleObserver(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onSuccess(@NonNull T o) {

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
