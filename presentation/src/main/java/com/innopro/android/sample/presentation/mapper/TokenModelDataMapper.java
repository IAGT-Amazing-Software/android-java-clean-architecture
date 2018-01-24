package com.innopro.android.sample.presentation.mapper;


import com.innopro.android.sample.domain.Token;
import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.model.TokenModel;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Token} (in the domain layer) to {@link TokenModel} in the
 * presentation layer.
 */
@PerActivity
public class TokenModelDataMapper {
    //region Constants
    private static final String TAG = TokenModelDataMapper.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    @Inject
    public TokenModelDataMapper() {
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Transform a {@link Token} into an {@link TokenModel}.
     *
     * @param token Object to be transformed.
     * @return {@link TokenModel}.
     */
    public TokenModel transform(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        TokenModel tokenModel = new TokenModel(token.getValue(), token.getExpiresIn(), token.getType(), token.getScope());
        return tokenModel;
    }

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
