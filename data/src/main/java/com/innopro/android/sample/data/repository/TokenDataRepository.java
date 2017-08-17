package com.innopro.android.sample.data.repository;

import com.innopro.android.sample.data.entity.mapper.TokenEntityDataMapper;
import com.innopro.android.sample.data.repository.datasource.TokenDataStoreFactory;
import com.innopro.android.sample.data.repository.datasource.source.TokenDataStore;
import com.innopro.android.sample.domain.Token;
import com.innopro.android.sample.domain.repository.TokenRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link TokenRepository} for retrieving user logged data.
 */
@Singleton
public class TokenDataRepository implements TokenRepository {

    private final TokenDataStoreFactory tokenDataStoreFactory;
    private final TokenEntityDataMapper tokenEntityDataMapper;

    /**
     * Constructs a {@link TokenRepository}.
     *
     * @param dataStoreFactory      A factory to construct different data source implementations.
     * @param tokenEntityDataMapper {@link TokenEntityDataMapper}.
     */
    @Inject
    public TokenDataRepository(TokenDataStoreFactory dataStoreFactory,
                               TokenEntityDataMapper tokenEntityDataMapper) {
        this.tokenDataStoreFactory = dataStoreFactory;
        this.tokenEntityDataMapper = tokenEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<Token> token() {
        final TokenDataStore tokenDataStore = this.tokenDataStoreFactory.create();
        return tokenDataStore.tokenEntity()
                .map(tokenEntity -> this.tokenEntityDataMapper.transform(tokenEntity));
    }
}