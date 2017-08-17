package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.cache.TokenCache;
import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;
import com.innopro.android.sample.data.repository.datasource.source.TokenDataStore;

import io.reactivex.Observable;

/**
 * {@link TokenDataStore} implementation based on connections to the api (Cloud).
 */

public class CloudTokenDataStore implements TokenDataStore {

    private final RestApi restApi;
    private final TokenCache tokenCache;

    /**
     * Construct a {@link MessageDataStore} based on connections to the api (Cloud).
     *
     * @param restApi    The {@link RestApi} implementation to use.
     * @param tokenCache A {@link TokenCache} to cache data retrieved from the api.
     */
    public CloudTokenDataStore(RestApi restApi, TokenCache tokenCache) {
        this.restApi = restApi;
        this.tokenCache = tokenCache;
    }

    @Override
    public Observable<TokenEntity> tokenEntity() {
        return this.restApi.tokenEntity().doOnNext(tokenCache::put);
    }


}
