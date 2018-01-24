package com.innopro.android.sample.data.repository.datasource.source.disk;

import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.entity.UserLoggedEntity;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserLoggedDataStore;

import io.reactivex.Observable;

/**
 * {@link UserLoggedDataStore} implementation based on file system data store.
 */
public class DiskUserLoggedDataStore implements UserLoggedDataStore {
    //region Constants
    private static final String TAG = DiskUserLoggedDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final UserLoggedCache userLoggedCache;

    //endregion

    //region Constructors & Initialization
    /**
     * Construct a {@link UserDataStore} based file system data store.
     *
     * @param userLoggedCache A {@link UserLoggedCache} to cache data retrieved from the api.
     */
    public DiskUserLoggedDataStore(UserLoggedCache userLoggedCache) {
        this.userLoggedCache = userLoggedCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<UserLoggedEntity> userLoggedEntity() {
        return this.userLoggedCache.get();
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
