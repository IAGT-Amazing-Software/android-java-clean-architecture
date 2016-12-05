package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.UserRepository;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class GetUserDetails extends UseCase {

  private final int userId;
  private final UserRepository userRepository;

  @Inject
  public GetUserDetails(int userId, UserRepository userRepository,
                        ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userId = userId;
    this.userRepository = userRepository;
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.userRepository.user(this.userId);
  }
}
