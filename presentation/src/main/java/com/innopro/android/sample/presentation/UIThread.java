package com.innopro.android.sample.presentation;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@Singleton
public class UIThread implements PostExecutionThread {

  @Inject
  public UIThread() {}

  @Override public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
