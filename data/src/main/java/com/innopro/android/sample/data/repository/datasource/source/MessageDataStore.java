package com.innopro.android.sample.data.repository.datasource.source;

import com.innopro.android.sample.data.entity.MessageEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface MessageDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link MessageEntity}.
   */
  Observable<List<MessageEntity>> messageEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link MessageEntity} by its id.
   *
   * @param messageId The id to retrieve message data.
   */
  Observable<MessageEntity> messageEntityDetails(final int messageId);
}
