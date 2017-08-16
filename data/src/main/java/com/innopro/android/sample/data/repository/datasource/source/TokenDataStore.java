package com.innopro.android.sample.data.repository.datasource.source;


import com.innopro.android.sample.data.entity.TokenEntity;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface TokenDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link TokenEntity}.
     */
    Observable<TokenEntity> tokenEntity();
}
