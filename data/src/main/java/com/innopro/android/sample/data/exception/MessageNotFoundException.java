package com.innopro.android.sample.data.exception;

/**
 * Exception throw by the application when a Message search can't return a valid result.
 */
public class MessageNotFoundException extends Exception {

  public MessageNotFoundException() {
    super();
  }

  public MessageNotFoundException(final String message) {
    super(message);
  }

  public MessageNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public MessageNotFoundException(final Throwable cause) {
    super(cause);
  }
}
