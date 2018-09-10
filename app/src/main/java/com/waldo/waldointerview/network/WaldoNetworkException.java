package com.waldo.waldointerview.network;

/**
 * Created by useruser on 9/10/18.
 */

public class WaldoNetworkException extends Exception {

    public WaldoNetworkException(final String message) {
        super(message);
    }

    public WaldoNetworkException(final Exception message) {
        super(message);
    }

}
