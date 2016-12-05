package com.innopro.android.sample.data.exception;

/**
 * Exception throw by the application when a Message search can't return a valid result.
 */
public class CategoryNotFoundException extends Exception {

  public CategoryNotFoundException() {
    super();
  }

  public CategoryNotFoundException(final String message) {
    super(message);
  }

  public CategoryNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CategoryNotFoundException(final Throwable cause) {
    super(cause);
  }
}
