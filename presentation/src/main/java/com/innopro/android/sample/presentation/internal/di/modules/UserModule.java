package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.interactor.GetUserDetails;
import com.innopro.android.sample.domain.interactor.GetUserList;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.domain.repository.UserRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {



  public UserModule() {}


  @Provides
  @Named("userList")
  UseCase provideGetUserListUseCase(
      GetUserList getUserList) {
    return getUserList;
  }

  @Provides @Named("userDetails")
  UseCase provideGetUserDetailsUseCase(
          UserRepository userRepository, PostExecutionThread postExecutionThread) {
    return new GetUserDetails(userRepository, postExecutionThread);
  }
}