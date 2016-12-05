package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link MessageDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudMessageDataStore implements MessageDataStore {

  private final RestApi restApi;
  private final MessageCache messageCache;

  private final Action1<MessageEntity> saveToCacheAction = messageEntity -> {
    if (messageEntity != null) {
      CloudMessageDataStore.this.messageCache.put(messageEntity);
    }
  };

  /**
   * Construct a {@link MessageDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param messageCache A {@link UserCache} to cache data retrieved from the api.
   */
  public CloudMessageDataStore(RestApi restApi, MessageCache messageCache) {
    this.restApi = restApi;
    this.messageCache = messageCache;
  }

  @Override public Observable<List<MessageEntity>> messageEntityList() {
    return this.restApi.messageEntityList();
  }

  @Override public Observable<MessageEntity> messageEntityDetails(final int messageId) {
    return this.restApi.messageEntityById(messageId).doOnNext(saveToCacheAction);
  }
}
