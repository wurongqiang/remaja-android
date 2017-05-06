package org.gerejajkt.remaja.utils.events;

/**
 * Created by wurongqiang on 5/6/17.
 */

public class QRCodeScannedEvent {

    private String value;

    public QRCodeScannedEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
