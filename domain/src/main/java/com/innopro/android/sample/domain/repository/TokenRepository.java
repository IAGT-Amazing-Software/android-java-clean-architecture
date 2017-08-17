package com.innopro.android.sample.domain.repository;

import com.innopro.android.sample.domain.Token;
import com.innopro.android.sample.domain.User;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface TokenRepository {
    /**
     * Get an {@link Observable} which will emit a {@link Token}.
     */
    Observable<Token> token();
}