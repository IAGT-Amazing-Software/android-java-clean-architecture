package com.innopro.android.sample.domain.repository;

import com.innopro.android.sample.domain.UserLogged;

import io.reactivex.Observable;


/**
 * Interface that represents a Repository for getting {@link UserLogged} related data.
 */
public interface UserLoggedRepository {
  /**
   * Get an {@link Observable} which will emit a {@link UserLogged}.
   *
   */
  Observable<UserLogged> userLogged();
}
