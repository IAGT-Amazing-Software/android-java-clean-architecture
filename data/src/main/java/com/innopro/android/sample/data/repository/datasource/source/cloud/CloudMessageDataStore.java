package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link MessageDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudMessageDataStore implements MessageDataStore {
    //region Constants
    private static final String TAG = CloudMessageDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final RestApi restApi;
    private final MessageCache messageCache;

    //endregion

    //region Constructors & Initialization
    /**
     * Construct a {@link MessageDataStore} based on connections to the api (Cloud).
     *
     * @param restApi      The {@link RestApi} implementation to use.
     * @param messageCache A {@link UserCache} to cache data retrieved from the api.
     */
    public CloudMessageDataStore(RestApi restApi, MessageCache messageCache) {
        this.restApi = restApi;
        this.messageCache = messageCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<List<MessageEntity>> messageEntityList() {
        return this.restApi.messageEntityList();
    }

    @Override
    public Observable<MessageEntity> messageEntityDetails(final int messageId) {
        return this.restApi.messageEntityById(messageId).doOnNext(messageCache::put);
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
