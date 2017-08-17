package com.innopro.android.sample.data.exception;

/**
 * Created by iagt on 6/07/17.
 */

public class TokenNotFoundException extends Exception {

    public TokenNotFoundException() {
        super();
    }

    public TokenNotFoundException(final String message) {
        super(message);
    }

    public TokenNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(final Throwable cause) {
        super(cause);
    }
}
