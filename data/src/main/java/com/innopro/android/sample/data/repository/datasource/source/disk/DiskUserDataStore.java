package com.innopro.android.sample.data.repository.datasource.source.disk;

import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;

import java.util.List;
import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
public class DiskUserDataStore implements UserDataStore {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  public DiskUserDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override public Observable<List<UserEntity>> userEntityList() {
    //TODO: implement simple cache for storing/retrieving collections of users.
    throw new UnsupportedOperationException("Operation is not available!!!");
  }

  @Override public Observable<UserEntity> userEntityDetails(final int userId) {
     return this.userCache.get(userId);
  }
}
