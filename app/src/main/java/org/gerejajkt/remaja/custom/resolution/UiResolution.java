package org.gerejajkt.remaja.custom.resolution;

import org.gerejajkt.remaja.data.api.responses.ErrorResponse;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by huteri on 1/9/17.
 */

public class UiResolution extends ResolutionByCase {
    private UiResolver uiResolver;

    @Inject
    public UiResolution(UiResolver uiResolver) {
        this.uiResolver = uiResolver;
    }

    @Override
    protected void onUnauthorized(ErrorResponse error) {

    }

    @Override
    public void onForbidden(ErrorResponse error) {
    }

    @Override
    protected void onUndefinedCode(Response response, ErrorResponse error) {
        uiResolver.showMesssage(error.message);
    }

    @Override
    public void onNetworkException() {
        uiResolver.showConnectionError();
    }
}
