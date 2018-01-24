package com.innopro.android.sample.data.repository.datasource;

import android.content.Context;

import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.net.RestApiImpl;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.cloud.CloudMessageDataStore;
import com.innopro.android.sample.data.repository.datasource.source.disk.DiskMessageDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class MessageDataStoreFactory {
    //region Constants
    private static final String TAG = MessageDataStoreFactory.class.getSimpleName();
    //endregion

    //region Fields
    private final Context context;
    private final MessageCache messageCache;

    //endregion

    //region Constructors & Initialization
    @Inject
    public MessageDataStoreFactory(Context context, MessageCache messageCache) {
        if (context == null || messageCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.messageCache = messageCache;
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Create {@link MessageDataStore} from a message id.
     */
    public MessageDataStore create(int messageId) {
        MessageDataStore messageDataStore;

        if (!this.messageCache.isExpired(messageId) && this.messageCache.isCached(messageId)) {
            messageDataStore = new DiskMessageDataStore(this.messageCache);
        } else {
            messageDataStore = createCloudDataStore();
        }

        return messageDataStore;
    }

    /**
     * Create {@link MessageDataStore} to retrieve data from the Cloud.
     */
    public MessageDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(this.context);

        return new CloudMessageDataStore(restApi, this.messageCache);
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
