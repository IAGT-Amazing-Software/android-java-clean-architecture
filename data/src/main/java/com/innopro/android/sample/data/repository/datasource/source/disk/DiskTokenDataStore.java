package com.innopro.android.sample.data.repository.datasource.source.disk;

import com.innopro.android.sample.data.cache.TokenCache;
import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.data.repository.datasource.source.TokenDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;

import io.reactivex.Observable;

/**
 * {@link TokenDataStore} implementation based on file system data store.
 */
public class DiskTokenDataStore implements TokenDataStore {
    //region Constants
    private static final String TAG = DiskTokenDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final TokenCache TokenCache;

    //endregion

    //region Constructors & Initialization
    /**
     * Construct a {@link UserDataStore} based file system data store.
     *
     * @param TokenCache A {@link TokenCache} to cache data retrieved from the api.
     */
    public DiskTokenDataStore(TokenCache TokenCache) {
        this.TokenCache = TokenCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override public Observable<TokenEntity> tokenEntity() {
        return this.TokenCache.get();
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion




}
