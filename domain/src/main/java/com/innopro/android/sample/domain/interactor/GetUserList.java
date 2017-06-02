package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.domain.executor.PostExecutionThread;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserList extends UseCase<List<User>,Void> {

  private final UserRepository userRepository;

  @Inject
  public GetUserList(UserRepository userRepository,
      PostExecutionThread postExecutionThread) {
    super( postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override public Observable buildUseCaseObservable(Void nothing) {
    return this.userRepository.users();
  }
}
