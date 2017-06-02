package com.innopro.android.sample.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultSubscriber<T> extends DisposableObserver<T> {

  @Override public void onError(Throwable e) {
    // no-op by default.
  }

  @Override
  public void onComplete() {

  }

  @Override public void onNext(T t) {
    // no-op by default.
  }
}
