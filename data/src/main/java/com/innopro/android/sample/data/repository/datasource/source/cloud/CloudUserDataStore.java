package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;

import java.util.List;
import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

  private final RestApi restApi;
  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  public CloudUserDataStore(RestApi restApi, UserCache userCache) {
    this.restApi = restApi;
    this.userCache = userCache;
  }

  @Override public Observable<List<UserEntity>> userEntityList() {
    return this.restApi.userEntityList();
  }

  @Override public Observable<UserEntity> userEntityDetails(final int userId) {
    return this.restApi.userEntityById(userId).doOnNext(userCache::put);
  }
}
