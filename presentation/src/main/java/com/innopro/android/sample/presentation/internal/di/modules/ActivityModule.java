package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {

  private final BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }

  /**
  * Expose the activity to dependents in the graph.
  */
  @Provides @PerActivity
  BaseActivity activity() {
    return this.activity;
  }
}
