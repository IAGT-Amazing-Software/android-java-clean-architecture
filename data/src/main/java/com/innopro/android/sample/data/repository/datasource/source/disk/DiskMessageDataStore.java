package com.innopro.android.sample.data.repository.datasource.source.disk;

import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link MessageDataStore} implementation based on file system data store.
 */
public class DiskMessageDataStore implements MessageDataStore {
    //region Constants
    private static final String TAG = DiskMessageDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final MessageCache messageCache;

    //endregion

    //region Constructors & Initialization
    /**
     * Construct a {@link UserDataStore} based file system data store.
     *
     * @param messageCache A {@link UserCache} to cache data retrieved from the api.
     */
    public DiskMessageDataStore(MessageCache messageCache) {
        this.messageCache = messageCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<List<MessageEntity>> messageEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of messages.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<MessageEntity> messageEntityDetails(final int messageId) {
        return this.messageCache.get(messageId);
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
