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
    //region Constants
    private static final String TAG = TokenDataRepository.class.getSimpleName();
    //endregion

    //region Fields
    private final TokenDataStoreFactory tokenDataStoreFactory;
    private final TokenEntityDataMapper tokenEntityDataMapper;

    //endregion

    //region Constructors & Initialization

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

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<Token> token() {
        final TokenDataStore tokenDataStore = this.tokenDataStoreFactory.create();
        return tokenDataStore.tokenEntity()
                .map(tokenEntity -> this.tokenEntityDataMapper.transform(tokenEntity));
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}