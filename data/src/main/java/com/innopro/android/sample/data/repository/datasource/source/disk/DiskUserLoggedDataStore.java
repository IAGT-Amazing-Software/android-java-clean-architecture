package com.innopro.android.sample.data.repository.datasource.source.disk;

import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.entity.UserLoggedEntity;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserLoggedDataStore;

import rx.Observable;

/**
 * {@link UserLoggedDataStore} implementation based on file system data store.
 */
public class DiskUserLoggedDataStore implements UserLoggedDataStore {

  private final UserLoggedCache userLoggedCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param userLoggedCache A {@link UserLoggedCache} to cache data retrieved from the api.
   */
  public DiskUserLoggedDataStore(UserLoggedCache userLoggedCache) {
    this.userLoggedCache = userLoggedCache;
  }

  @Override public Observable<UserLoggedEntity> userLoggedEntity() {
     return this.userLoggedCache.get();
  }
}
