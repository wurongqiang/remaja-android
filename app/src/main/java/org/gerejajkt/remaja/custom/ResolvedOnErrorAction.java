package org.gerejajkt.remaja.custom;

import org.gerejajkt.remaja.custom.resolution.Resolution;
import org.gerejajkt.remaja.data.api.responses.ErrorResponse;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by huteri on 1/10/17.
 */

public class ResolvedOnErrorAction implements Consumer<Throwable> {

    private Resolution resolution;

    public ResolvedOnErrorAction(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        RetrofitException error = (RetrofitException) throwable;

        if(error.getKind() == RetrofitException.Kind.NETWORK) {
            resolution.onNetworkException();
        } else if(error.getKind() == RetrofitException.Kind.HTTP) {
            ErrorResponse errorBodyAs = null;
            try {
                errorBodyAs = error.getErrorBodyAs(ErrorResponse.class);
                resolution.onHttpException(error.getResponse(), errorBodyAs);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        throwable.printStackTrace();
    }
}
