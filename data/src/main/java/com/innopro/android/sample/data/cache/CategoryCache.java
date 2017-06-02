package com.innopro.android.sample.data.cache;

import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;

import io.reactivex.Observable;

/**
 * An interface representing a message Cache.
 */
public interface CategoryCache {
  /**
   * Gets an {@link Observable} which will emit a {@link MessageEntity}.
   *
   * @param categoryId The message id to retrieve data.
   */
  Observable<CategoryEntity> get(final int categoryId);

  /**
   * Puts and element into the cache.
   *
   * @param categoryEntity Element to insert in the cache.
   */
  void put(CategoryEntity categoryEntity);

  /**
   * Checks if an element (Message) exists in the cache.
   *
   * @param categoryId The id message to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  boolean isCached(final int categoryId);

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  boolean isExpired(int categoryId);

  /**
   * Evict all elements of the cache.
   */
  void evictAll(int categoryId);
}
