package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.interactor.GetUserLogged;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;
import com.innopro.android.sample.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserLoggedModule {

  public UserLoggedModule() {}

  @Provides @Named("userLogged")
  UseCase provideGetUserLoggedUseCase(
          UserLoggedRepository userLoggedRepository, PostExecutionThread postExecutionThread) {
    return new GetUserLogged(userLoggedRepository, postExecutionThread);
  }
}