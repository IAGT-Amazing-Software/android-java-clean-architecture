package com.innopro.android.sample.data.repository.datasource;

import android.content.Context;

import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.net.RestApiImpl;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.cloud.CloudUserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.disk.DiskUserDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {
    //region Constants
    private static final String TAG = UserDataStoreFactory.class.getSimpleName();
    //endregion

    //region Fields
    private final Context context;
    private final UserCache userCache;

    //endregion

    //region Constructors & Initialization
    @Inject
    public UserDataStoreFactory(Context context, UserCache userCache) {
        if (context == null || userCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Create {@link UserDataStore} from a user id.
     */
    public UserDataStore create(int userId) {
        UserDataStore userDataStore;

        if (!this.userCache.isExpired(userId) && this.userCache.isCached(userId)) {
            userDataStore = new DiskUserDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     */
    public UserDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(this.context);

        return new CloudUserDataStore(restApi, this.userCache);
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
