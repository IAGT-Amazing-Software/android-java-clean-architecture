package com.innopro.android.sample.data.cache;

import com.innopro.android.sample.data.entity.UserLoggedEntity;

import rx.Observable;

/**
 * An interface representing a user logged Cache.
 */
public interface UserLoggedCache {
  /**
   * Gets an {@link Observable} which will emit a {@link UserLoggedEntity}.
   *
   */
  Observable<UserLoggedEntity> get();

  /**
   * Puts and element into the cache.
   *
   * @param userLoggedEntity Element to insert in the cache.
   */
  void put(UserLoggedEntity userLoggedEntity);

  /**
   * Checks if an element (UserLogged) exists in the cache.
   *
   * @return true if the element is cached, otherwise false.
   */
  boolean isCached();

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  boolean isExpired();

  /**
   * Evict all elements of the cache.
   */
  void evictAll();
}
