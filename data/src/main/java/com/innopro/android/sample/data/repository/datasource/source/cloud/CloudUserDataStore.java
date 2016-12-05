package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;

import java.util.List;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

  private final RestApi restApi;
  private final UserCache userCache;

  private final Action1<UserEntity> saveToCacheAction = userEntity -> {
    if (userEntity != null) {
      CloudUserDataStore.this.userCache.put(userEntity);
    }
  };

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
    return this.restApi.userEntityById(userId).doOnNext(saveToCacheAction);
  }
}
