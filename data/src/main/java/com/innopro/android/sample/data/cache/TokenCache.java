package com.innopro.android.sample.data.cache;


import com.innopro.android.sample.data.entity.TokenEntity;

import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */
public interface TokenCache {
    /**
     * Gets an {@link Observable} which will emit a {@link TokenEntity}.
     */
    Observable<TokenEntity> get();

    /**
     * Puts and element into the cache.
     *
     * @param tokenEntity Element to insert in the cache.
     */
    void put(TokenEntity tokenEntity);

    /**
     * Checks if an element (Token) exists in the cache.
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
