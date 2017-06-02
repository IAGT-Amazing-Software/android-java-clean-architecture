package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.entity.UserLoggedEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserLoggedDataStore;

import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserLoggedDataStore implements UserLoggedDataStore {

  private final RestApi restApi;
  private final UserLoggedCache userLoggedCache;

  /**
   * Construct a {@link UserLoggedDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param userLoggedCache A {@link UserLoggedCache} to cache data retrieved from the api.
   */
  public CloudUserLoggedDataStore(RestApi restApi, UserLoggedCache userLoggedCache) {
    this.restApi = restApi;
    this.userLoggedCache = userLoggedCache;
  }


  @Override public Observable<UserLoggedEntity> userLoggedEntity() {
    return this.restApi.userLoggedEntity().doOnNext(userLoggedCache::put);
  }
}
