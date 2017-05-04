package org.gerejajkt.remaja.custom.resolution;


import org.gerejajkt.remaja.data.api.responses.ErrorResponse;

import retrofit2.Response;

/**
 * Created by huteri on 1/9/17.
 */

public interface Resolution {

    void onHttpException(Response response, ErrorResponse error);

    void onNetworkException();
}
