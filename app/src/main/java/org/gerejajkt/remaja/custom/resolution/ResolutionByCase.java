package org.gerejajkt.remaja.custom.resolution;

import org.gerejajkt.remaja.data.api.responses.ErrorResponse;

import retrofit2.Response;

/**
 * Created by huteri on 1/9/17.
 */

public abstract class ResolutionByCase implements Resolution {

    @Override
    public void onHttpException(Response response, ErrorResponse error) {

        switch (response.code()) {
            case 403:
                onForbidden(error);
                break;
            case 401:
                onUnauthorized(error);
            default:
                onUndefinedCode(response, error);
                break;

        }
    }

    protected abstract void onUnauthorized(ErrorResponse error);

    protected abstract void onForbidden(ErrorResponse error);

    protected abstract void onUndefinedCode(Response response, ErrorResponse error);
}
