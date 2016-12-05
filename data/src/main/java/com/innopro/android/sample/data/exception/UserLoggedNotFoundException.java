package com.innopro.android.sample.data.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */
public class UserLoggedNotFoundException extends Exception {

  public UserLoggedNotFoundException() {
    super();
  }

  public UserLoggedNotFoundException(final String message) {
    super(message);
  }

  public UserLoggedNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public UserLoggedNotFoundException(final Throwable cause) {
    super(cause);
  }
}
