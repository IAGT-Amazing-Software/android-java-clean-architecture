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

    private final TokenCache TokenCache;

    /**
     * Construct a {@link UserDataStore} based file system data store.
     *
     * @param TokenCache A {@link TokenCache} to cache data retrieved from the api.
     */
    public DiskTokenDataStore(TokenCache TokenCache) {
        this.TokenCache = TokenCache;
    }



    @Override public Observable<TokenEntity> tokenEntity() {
        return this.TokenCache.get();
    }
}
