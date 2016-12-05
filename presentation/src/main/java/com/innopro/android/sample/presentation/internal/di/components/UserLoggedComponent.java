package com.innopro.android.sample.presentation.internal.di.components;

import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.internal.di.modules.ActivityModule;
import com.innopro.android.sample.presentation.internal.di.modules.UserLoggedModule;
import com.innopro.android.sample.presentation.view.fragment.UserLoggedFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserLoggedModule.class})
public interface UserLoggedComponent extends ActivityComponent {
    void inject(UserLoggedFragment userLoggedFragment);
}
