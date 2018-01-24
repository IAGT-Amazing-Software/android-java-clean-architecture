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
    //region Constants
    private static final String TAG = CloudTokenDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final RestApi restApi;
    private final TokenCache tokenCache;

    //endregion

    //region Constructors & Initialization
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

    //endregion

    //region Methods for/from SuperClass/Interfaces

    @Override
    public Observable<TokenEntity> tokenEntity() {
        return this.restApi.tokenEntity().doOnNext(tokenCache::put);
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
