package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.interactor.GetUserDetails;
import com.innopro.android.sample.domain.interactor.GetUserList;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

  private int userId = -1;

  public UserModule() {}

  public UserModule(int userId) {
    this.userId = userId;
  }

  @Provides @PerActivity
  @Named("userList")
  UseCase provideGetUserListUseCase(
      GetUserList getUserList) {
    return getUserList;
  }

  @Provides @PerActivity @Named("userDetails")
  UseCase provideGetUserDetailsUseCase(
          UserRepository userRepository, ThreadExecutor threadExecutor,
          PostExecutionThread postExecutionThread) {
    return new GetUserDetails(userId, userRepository, threadExecutor, postExecutionThread);
  }
}