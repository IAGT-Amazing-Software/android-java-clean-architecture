package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class GetUserDetails extends UseCase<User,GetUserDetails.Params> {

  private final UserRepository userRepository;

  @Inject
  public GetUserDetails( UserRepository userRepository, PostExecutionThread postExecutionThread) {
    super( postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override protected Observable buildUseCaseObservable(Params params) {
    return this.userRepository.user(params.userId);
  }

  public static final class Params {
    private final int userId;
    private Params(int userId) {
      this.userId = userId;
    }

    public static Params forGetUserFiles(int userId) {
      return new Params(userId);
    }
  }
}
