package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserList extends UseCase {

  private final UserRepository userRepository;

  @Inject
  public GetUserList(UserRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.userRepository.users();
  }
}
