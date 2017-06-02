package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.UserLogged;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link UserLogged}.
 */
public class GetUserLogged extends UseCase<UserLogged,Void> {
  private final UserLoggedRepository userLoggedRepository;

  @Inject
  public GetUserLogged(UserLoggedRepository userLoggedRepository,
                        PostExecutionThread postExecutionThread) {
    super( postExecutionThread);
    this.userLoggedRepository = userLoggedRepository;
  }

  @Override protected Observable buildUseCaseObservable(Void nothing) {
    return this.userLoggedRepository.userLogged();
  }
}
