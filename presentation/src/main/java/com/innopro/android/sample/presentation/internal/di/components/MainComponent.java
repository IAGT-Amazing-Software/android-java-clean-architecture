package com.innopro.android.sample.presentation.internal.di.components;

import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.internal.di.modules.ActivityModule;
import com.innopro.android.sample.presentation.internal.di.modules.MessageModule;
import com.innopro.android.sample.presentation.internal.di.modules.TokenModule;
import com.innopro.android.sample.presentation.internal.di.modules.UserLoggedModule;
import com.innopro.android.sample.presentation.internal.di.modules.UserModule;
import com.innopro.android.sample.presentation.view.fragment.MessageCategoryFragment;
import com.innopro.android.sample.presentation.view.fragment.MessageDetailsFragment;
import com.innopro.android.sample.presentation.view.fragment.MessageListFragment;
import com.innopro.android.sample.presentation.view.fragment.UserDetailsFragment;
import com.innopro.android.sample.presentation.view.fragment.UserListFragment;
import com.innopro.android.sample.presentation.view.fragment.UserLoggedFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MessageModule.class, UserModule.class, UserLoggedModule.class, TokenModule.class})
public interface MainComponent extends ActivityComponent {
  void inject(MessageCategoryFragment messageCategoryFragment);

  void inject(MessageListFragment messageListFragment);
  void inject(MessageDetailsFragment messageDetailsFragment);

  void inject(UserListFragment userListFragment);
  void inject(UserDetailsFragment userDetailsFragment);

  void inject(UserLoggedFragment userLoggedFragment);
}
