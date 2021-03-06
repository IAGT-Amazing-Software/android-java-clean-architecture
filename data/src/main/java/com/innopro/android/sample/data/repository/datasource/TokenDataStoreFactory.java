package com.innopro.android.sample.data.repository.datasource;

import android.content.Context;

import com.innopro.android.sample.data.cache.TokenCache;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.net.RestApiImpl;
import com.innopro.android.sample.data.repository.datasource.source.TokenDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.cloud.CloudTokenDataStore;
import com.innopro.android.sample.data.repository.datasource.source.disk.DiskTokenDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class TokenDataStoreFactory {
    //region Constants
    private static final String TAG = TokenDataStoreFactory.class.getSimpleName();
    //endregion

    //region Fields
    private final Context context;
    private final TokenCache tokenCache;
    //endregion

    //region Constructors & Initialization
    @Inject
    public TokenDataStoreFactory(Context context, TokenCache TokenCache) {
        if (context == null || TokenCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.tokenCache = TokenCache;
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Create {@link TokenDataStore} from a Token id.
     */
    public TokenDataStore create() {
        TokenDataStore tokenDataStore;
        if (!this.tokenCache.isExpired()) {
            tokenDataStore = new DiskTokenDataStore(this.tokenCache);
        } else {
            tokenDataStore = createCloudDataStore();
        }

        return tokenDataStore;
    }

    /**
     * Create {@link TokenDataStore} to retrieve data from the Cloud.
     */
    public TokenDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(this.context);

        return new CloudTokenDataStore(restApi, this.tokenCache);
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion



}
