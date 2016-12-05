package com.innopro.android.sample.data.repository.datasource.source;

import com.innopro.android.sample.data.entity.UserLoggedEntity;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserLoggedDataStore {
  /**
   * Get an {@link Observable} which will emit a {@link UserLoggedEntity}.
   *
   */
  Observable<UserLoggedEntity> userLoggedEntity();
}
