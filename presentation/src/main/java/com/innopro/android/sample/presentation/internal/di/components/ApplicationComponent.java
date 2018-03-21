package com.innopro.android.sample.presentation.internal.di.components;

import android.content.Context;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.CategoryRepository;
import com.innopro.android.sample.domain.repository.MessageRepository;
import com.innopro.android.sample.domain.repository.TokenRepository;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.presentation.internal.di.modules.ApplicationModule;
import com.innopro.android.sample.presentation.internal.di.modules.TokenModule;
import com.innopro.android.sample.presentation.navigation.Navigator;
import com.innopro.android.sample.presentation.utils.SharedPreferencesManager;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;
import com.innopro.android.sample.presentation.view.activity.MainActivity;
import com.innopro.android.sample.presentation.view.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, TokenModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);
    void inject(BaseFragment baseFragment);

    //Exposed to sub-graphs.
    Context context();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();

    MessageRepository messageRepository();

    CategoryRepository categoryRepository();

    UserLoggedRepository userLoggedRepository();

    TokenRepository provideTokenRepository();

    SharedPreferencesManager provideSharedPreferencesManager();

    Navigator provideNavigator();
}
