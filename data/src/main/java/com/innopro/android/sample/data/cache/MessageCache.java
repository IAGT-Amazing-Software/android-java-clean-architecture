package com.innopro.android.sample.data.cache;

import com.innopro.android.sample.data.entity.MessageEntity;

import io.reactivex.Observable;

/**
 * An interface representing a message Cache.
 */
public interface MessageCache {
  /**
   * Gets an {@link Observable} which will emit a {@link MessageEntity}.
   *
   * @param messageId The message id to retrieve data.
   */
  Observable<MessageEntity> get(final int messageId);

  /**
   * Puts and element into the cache.
   *
   * @param messageEntity Element to insert in the cache.
   */
  void put(MessageEntity messageEntity);

  /**
   * Checks if an element (Message) exists in the cache.
   *
   * @param messageId The id message to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  boolean isCached(final int messageId);

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  boolean isExpired(int messageId);

  /**
   * Evict all elements of the cache.
   */
  void evictAll(int messageId);
}
