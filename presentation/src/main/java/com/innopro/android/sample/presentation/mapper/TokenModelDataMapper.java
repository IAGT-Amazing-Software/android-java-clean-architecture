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

    @Inject
    public TokenModelDataMapper() {
    }

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

}
