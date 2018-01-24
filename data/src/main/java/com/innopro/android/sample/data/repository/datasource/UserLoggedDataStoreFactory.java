package com.innopro.android.sample.data.repository.datasource;

import android.content.Context;

import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.net.RestApiImpl;
import com.innopro.android.sample.data.repository.datasource.source.UserLoggedDataStore;
import com.innopro.android.sample.data.repository.datasource.source.cloud.CloudUserLoggedDataStore;
import com.innopro.android.sample.data.repository.datasource.source.disk.DiskUserLoggedDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserLoggedDataStore}.
 */
@Singleton
public class UserLoggedDataStoreFactory {
    //region Constants
    private static final String TAG = UserLoggedDataStoreFactory.class.getSimpleName();
    //endregion

    //region Fields
    private final Context context;
    private final UserLoggedCache userLoggedCache;

    //endregion

    //region Constructors & Initialization
    @Inject
    public UserLoggedDataStoreFactory(Context context, UserLoggedCache userLoggedCache) {
        if (context == null || userLoggedCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userLoggedCache = userLoggedCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Create {@link UserLoggedDataStore} from a user id.
     */
    public UserLoggedDataStore create() {
        UserLoggedDataStore userLoggedDataStore;

        if (!this.userLoggedCache.isExpired() && this.userLoggedCache.isCached()) {
            userLoggedDataStore = new DiskUserLoggedDataStore(this.userLoggedCache);
        } else {
            userLoggedDataStore = createCloudDataStore();
        }

        return userLoggedDataStore;
    }

    /**
     * Create {@link UserLoggedDataStore} to retrieve data from the Cloud.
     */
    public UserLoggedDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(this.context);

        return new CloudUserLoggedDataStore(restApi, this.userLoggedCache);
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
