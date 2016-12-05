package com.innopro.android.sample.presentation.internal.di.components;

import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.internal.di.modules.ActivityModule;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  BaseActivity activity();
}
