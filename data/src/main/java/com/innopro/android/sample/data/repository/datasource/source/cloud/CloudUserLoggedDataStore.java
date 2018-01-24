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
    //region Constants
    private static final String TAG = CloudUserLoggedDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final RestApi restApi;
    private final UserLoggedCache userLoggedCache;

    //endregion

    //region Constructors & Initialization

    /**
     * Construct a {@link UserLoggedDataStore} based on connections to the api (Cloud).
     *
     * @param restApi         The {@link RestApi} implementation to use.
     * @param userLoggedCache A {@link UserLoggedCache} to cache data retrieved from the api.
     */
    public CloudUserLoggedDataStore(RestApi restApi, UserLoggedCache userLoggedCache) {
        this.restApi = restApi;
        this.userLoggedCache = userLoggedCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<UserLoggedEntity> userLoggedEntity() {
        return this.restApi.userLoggedEntity().doOnNext(userLoggedCache::put);
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
