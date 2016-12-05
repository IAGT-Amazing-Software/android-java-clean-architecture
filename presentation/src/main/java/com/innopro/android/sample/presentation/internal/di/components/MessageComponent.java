package com.innopro.android.sample.presentation.internal.di.components;

import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.internal.di.modules.ActivityModule;
import com.innopro.android.sample.presentation.internal.di.modules.MessageModule;
import com.innopro.android.sample.presentation.view.fragment.MessageCategoryFragment;
import com.innopro.android.sample.presentation.view.fragment.MessageDetailsFragment;
import com.innopro.android.sample.presentation.view.fragment.MessageListFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MessageModule.class})
public interface MessageComponent extends ActivityComponent {
  void inject(MessageCategoryFragment messageCategoryFragment);
  void inject(MessageListFragment messageListFragment);
  void inject(MessageDetailsFragment messageDetailsFragment);
}
